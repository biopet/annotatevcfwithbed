/*
 * Copyright (c) 2014 Biopet
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

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
