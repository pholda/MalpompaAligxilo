package pl.pholda.malpompaaligxilo.util

import utest._

class DateTest(companion: DateCompanion) extends TestSuite {

  val tests = TestSuite{
    s"Dates: ${companion.getClass.getSimpleName}"-{
      'fromString{
        'firstDate{
          assertDate(1920, 1, 2, companion.fromString("1920-01-02"))
        }
//        'secondDate{
//          assertDate(100, 12, 31, companion.fromString("100-12-31"))
//        }
        'thirdDate{
          assertDate(2015, 2, 18, companion.fromString("2015-02-18"))
        }
        'fourthDate{
          assertDate(1999, 7, 21, companion.fromString("1999-07-21"))
        }
      }
      'compare{
        'first{
          val d1 = companion.fromString("1999-07-20")
          val d2 = companion.fromString("1999-08-01")
          assert(d1 < d2)
        }
        'second{
          val d1 = companion.fromString("1999-07-20")
          val d2 = companion.fromString("1999-07-20")
          assert(d1 <= d2)
        }
        'third{
          val d1 = companion.fromString("2015-01-13")
          val d2 = companion.fromString("1999-07-20")
          assert(d1 > d2)
        }
      }
      'daysTo{
        'first{
          val d1 = companion.fromString("1999-07-01")
          val d2 = companion.fromString("1999-07-21")
          assert((d1 daysTo d2) == 20)
          assert((d2 daysTo d1) == -20)
        }
        'second{
          val d1 = companion.fromString("2000-12-30")
          val d2 = companion.fromString("2001-01-15")
          assert((d1 daysTo d2) == 16)
          assert((d2 daysTo d1) == -16)
        }
        'third{
          val d1 = companion.fromString("2015-01-01")
          val d2 = companion.fromString("2015-01-02")
          assert((d1 daysTo d2) == 1)
          assert((d2 daysTo d1) == -1)
        }
        'fourth{
          val d1 = companion.fromString("2015-01-01")
          val d2 = companion.fromString("2015-01-01")
          assert((d1 daysTo d2) == 0)
          assert((d2 daysTo d1) == 0)
        }
        'fifth{
          val d1 = companion.fromString("2015-01-30")
          val d2 = companion.fromString("2015-01-15")
          assert((d1 daysTo d2) == -15)
          assert((d2 daysTo d1) == 15)
        }
      }
    }
  }

  protected def assertDate(year: Int, month: Int, day: Int, date: Date): Unit = {
    assert(date.getYear == year)
    assert(date.getMonth == month)
    assert(date.getDay == day)
  }
}
