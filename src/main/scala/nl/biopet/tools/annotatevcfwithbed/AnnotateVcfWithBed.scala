package nl.biopet.tools.annotatevcfwithbed

import htsjdk.variant.variantcontext.VariantContextBuilder
import htsjdk.variant.variantcontext.writer.{
  AsyncVariantContextWriter,
  VariantContextWriterBuilder
}
import htsjdk.variant.vcf.{
  VCFFileReader,
  VCFHeaderLineCount,
  VCFHeaderLineType,
  VCFInfoHeaderLine
}

import nl.biopet.utils.tool.ToolCommand
import nl.biopet.utils.ngs.intervals.{BedRecord, BedRecordList}

import scala.collection.JavaConversions._

object AnnotateVcfWithBed extends ToolCommand[Args] {
  def emptyArgs: Args = Args()
  def argsParser = new ArgsParser(this)
  def main(args: Array[String]): Unit = {
    val cmdArgs = cmdArrayToArgs(args)

    logger.info("Start")

    annotateVcfWithBed(cmdArgs)

    logger.info("Done")
  }

  def annotateVcfWithBed(cmdArgs: Args): Unit = {
    val fieldType = cmdArgs.fieldType match {
      case "Integer" => VCFHeaderLineType.Integer
      case "Flag" => VCFHeaderLineType.Flag
      case "Character" => VCFHeaderLineType.Character
      case "Float" => VCFHeaderLineType.Float
      case _ => VCFHeaderLineType.String
    }

    logger.info("Reading bed file")
    val bedRecords = BedRecordList.fromFile(cmdArgs.bedFile).sorted

    logger.info("Starting output file")

    val reader = new VCFFileReader(cmdArgs.inputFile, false)
    val header = reader.getFileHeader

    val writer = new AsyncVariantContextWriter(
      new VariantContextWriterBuilder()
        .setOutputFile(cmdArgs.outputFile)
        .setReferenceDictionary(header.getSequenceDictionary)
        .build)

    header.addMetaDataLine(
      new VCFInfoHeaderLine(cmdArgs.fieldName,
                            VCFHeaderLineCount.UNBOUNDED,
                            fieldType,
                            cmdArgs.fieldDescription))
    writer.writeHeader(header)

    logger.info("Start reading vcf records")

    for (record <- reader) {
      val overlaps =
        bedRecords.overlapWith(
          BedRecord(record.getContig, record.getStart, record.getEnd))
      if (overlaps.isEmpty) {
        writer.add(record)
      } else {
        val builder = new VariantContextBuilder(record)
        if (fieldType == VCFHeaderLineType.Flag)
          builder.attribute(cmdArgs.fieldName, true)
        else
          builder.attribute(cmdArgs.fieldName,
                            overlaps.map(_.name).mkString(","))
        writer.add(builder.make)
      }
    }
    reader.close()
    writer.close()
  }

  def descriptionText: String =
    """
      |This tool to annotates a vcf file using the input from a bed file.
    """.stripMargin

  def manualText: String =
    """
      |The result of this tool will be a vcf file with an extra field with annotation.
    """.stripMargin

  def exampleText: String =
    s"""
       |
       |${example("-I", "input.vcf", "-B", "inputBed.bed", "-o", "output.vcf")}
     """.stripMargin
}
