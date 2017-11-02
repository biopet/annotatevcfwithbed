package nl.biopet.tools.annotatevcfwithbed

import nl.biopet.utils.test.tools.ToolTest
import org.testng.annotations.Test

import scala.util.Random

class AnnotateVcfWithBedTest extends ToolTest[Args] {
  def toolCommand: AnnotateVcfWithBed.type = AnnotateVcfWithBed
  import AnnotateVcfWithBed._

  @Test
  def testNoArgs(): Unit = {
    intercept[IllegalArgumentException] {
      AnnotateVcfWithBed.main(Array())
    }
  }

  val veppedPath: String = resourcePath("/VEP_oneline.vcf")
  val bed: String = resourcePath("/rrna01.bed")
  val rand = new Random()

  @Test def testOutputTypeVcf(): Unit = {
    val tmpPath = "/tmp/VcfFilter_" + rand.nextString(10) + ".vcf"
    val arguments: Array[String] =
      Array("-I", veppedPath, "-o", tmpPath, "-B", bed, "-f", "testing")
    main(arguments)
  }

  @Test def testOutputTypeBcf(): Unit = {
    val tmpPath = "/tmp/VcfFilter_" + rand.nextString(10) + ".bcf"
    val arguments: Array[String] =
      Array("-I", veppedPath, "-o", tmpPath, "-B", bed, "-f", "testing")
    main(arguments)
  }

  @Test def testOutputTypeVcfGz(): Unit = {
    val tmpPath = "/tmp/VcfFilter_" + rand.nextString(10) + ".vcf.gz"
    val arguments: Array[String] =
      Array("-I", veppedPath, "-o", tmpPath, "-B", bed, "-f", "testing")
    main(arguments)
  }
}
