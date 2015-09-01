package pl.pholda.malpompaaligxilo.dsl

import org.joda.time.DateTime
import pl.pholda.malpompaaligxilo.util.{DateJVM, Date}

object DateExprParserJVMTest extends DateExprParserTest {
  override def testForm: TestForm = TestFormJVM

  override def isToday(d: Date): Boolean = {
    val today = new DateTime()

    d.getDay == today.dayOfMonth().get() &&
    d.getMonth == today.monthOfYear().get &&
    d.getYear == today.getYear
  }
}
