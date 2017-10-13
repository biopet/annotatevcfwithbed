package nl.biopet.tools.annotatevcfwithbed

import java.io.File

import nl.biopet.utils.tool.AbstractOptParser

class ArgsParser(cmdName: String) extends AbstractOptParser[Args](cmdName) {
  opt[File]('I', "inputFile") required () unbounded () valueName "<vcf file>" action { (x, c) =>
    c.copy(inputFile = x)
  } text "Input VCF file. Mandatory field"
  opt[File]('B', "bedFile") required () unbounded () valueName "<bed file>" action { (x, c) =>
    c.copy(bedFile = x)
  } text "Input Bed file. Mandatory field"
  opt[File]('o', "output") required () unbounded () valueName "<vcf file>" action { (x, c) =>
    c.copy(outputFile = x)
  } text "Output VCF file. Mandatory field"
  opt[String]('f', "fieldName") required () unbounded () valueName "<name of field in vcf file>" action {
    (x, c) =>
      c.copy(fieldName = x)
  } text "Name of info field in new vcf file"
  opt[String]('d', "fieldDescription") unbounded () valueName "<description of field in vcf file>" action {
    (x, c) =>
      c.copy(fieldDescription = x)
  } text "Description of field in new vcf file"
  opt[String]('t', "fieldType") unbounded () valueName "<type of field in vcf file>" action {
    (x, c) =>
      c.copy(fieldType = x)
  } text "Type of field in new vcf file. Can be 'Integer', 'Flag', 'Character', 'Float'"
}
