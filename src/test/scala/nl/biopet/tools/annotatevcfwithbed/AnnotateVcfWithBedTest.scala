package nl.biopet.tools.annotatevcfwithbed

import nl.biopet.test.BiopetTest
import org.testng.annotations.Test

class AnnotateVcfWithBedTest extends BiopetTest {
  @Test
  def testNoArgs(): Unit = {
    intercept[IllegalArgumentException] {
      AnnotateVcfWithBed.main(Array())
    }
  }
}
