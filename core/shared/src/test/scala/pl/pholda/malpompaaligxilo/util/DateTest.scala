package pl.pholda.malpompaaligxilo.util

import utest._

class DateTest(companion: DateCompanion) extends TestSuite {
  val tests = TestSuite{
    s"Dates: ${companion.getClass.getSimpleName}"-{
      "fromString"- {
        val dates = Seq(
          "1920-01-02" -> (1920, 1, 2),
          "100-12-31" -> (100, 12, 31),
          "2015-02-18" -> (2015, 2, 18),
          "1999-07-21" -> (1999, 7, 21)
        )
        dates.foreach{
          case (str, (year, month, day)) =>
            val date = companion.fromString(str)
            assert(date.getDay == day)
            assert(date.getMonth == month)
            assert(date.getYear == year)
        }
      }
      "comparing"-{
        val d1 = companion.fromString("1999-07-20")
        val d2 = companion.fromString("1999-08-01")
        assert(d1 < d2)
      }
    }
  }
}
