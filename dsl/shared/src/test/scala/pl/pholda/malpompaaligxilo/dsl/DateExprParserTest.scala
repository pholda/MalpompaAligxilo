package pl.pholda.malpompaaligxilo.dsl

import pl.pholda.malpompaaligxilo.dsl.parser.expr.FormExprParser
import pl.pholda.malpompaaligxilo.i18n.TranslationProvider
import pl.pholda.malpompaaligxilo.util.{Date, DateCompanion}
import utest._

trait DateExprParserTest extends TestSuite with ParserTestHelper[FormExprParser] {
  def testForm: TestForm

  def isToday(d: Date): Boolean

  override val parsers: FormExprParser = new FormExprParser {
    override implicit val translationProvider: TranslationProvider = testForm.context.translationProvider
    override implicit val dateCompanion: DateCompanion = testForm.context.date
  }

  import parsers._

  override def tests = TestSuite{
    'dateFieldValue{

      assertMatch(quickParse(fieldValue, """ $"date" """)(testForm.form)){
        case d: Date =>
          assertDate(2010, 1, 1, d)
      }
    }
    'dateFromString{
      assertMatch(quickParse(dateFromString, """ date ("2014-06-07") """)(testForm.form)){
        case d: Date =>
          assertDate(2014, 6, 7, d)
      }
    }
    'dateDiff{
      'days{
        assertMatch(quickParse(dateDiff,
          """
            |dateDiff ( days, $"date", date ("2014-06-07"))
          """.stripMargin)(testForm.form)){
          case 1618 =>
        }
      }
    }
    'datesCompare{
      'a{
        assertMatch(quickParse(dateCompare,
          """
            |compareDates ( $"date" > date ("2014-06-07"))
          """.stripMargin)(testForm.form)){
          case false =>
        }
      }
    }
    'today{
      assertMatch(quickParse(dateToday, "date ( today ) ")(testForm.form)){
        case d: Date if isToday(d) =>
      }
    }
  }

  protected def assertDate(year: Int, month: Int, day: Int, date: Date): Unit = {
    assert(date.getYear == year)
    assert(date.getMonth == month)
    assert(date.getDay == day)
  }

}
