package nl.biopet.tools.annotatevcfwithbed

import nl.biopet.test.BiopetTest
import org.testng.annotations.Test

object AnnotateVcfWithBedTest extends BiopetTest {
  @Test
  def testNoArgs(): Unit = {
    intercept[IllegalArgumentException] {
      ToolTemplate.main(Array())
    }
  }
}
