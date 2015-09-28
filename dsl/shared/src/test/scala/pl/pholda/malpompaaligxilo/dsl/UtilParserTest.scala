package pl.pholda.malpompaaligxilo.dsl



import pl.pholda.malpompaaligxilo.dsl.parser.UtilParsers
import pl.pholda.malpompaaligxilo.dsl.parser.expr.FormExprParser
import pl.pholda.malpompaaligxilo.i18n.TranslationProvider
import pl.pholda.malpompaaligxilo.util.{Date, DateCompanion}
import utest._

import scala.collection.Set

trait UtilParserTest extends TestSuite with ParserTestHelper[UtilParsers] {
  def testForm: TestForm

  override val parsers = new FormExprParser {
    override implicit val translationProvider: TranslationProvider = testForm.context.translationProvider
    override implicit val dateCompanion: DateCompanion = testForm.context.date
  }

  import parsers._

  val tests = TestSuite{
    'booleanLit{
      'true{
        val result: Boolean = quickParse(booleanLit, "true")
        assert(result)
      }
      'false{
        val result: Boolean = quickParse(booleanLit, "false")
        assert(!result)
      }
    }
    'dateLit{
      val date: Date = quickParse(dateLit, """ "1990-02-04" """)
      assert(date.getDay == 4)
      assert(date.getMonth == 2)
      assert(date.getYear == 1990)
    }
    'intLit{
      val int = quickParse(intLit, "123")
      assert(int == 123)
    }
    'set{
      val asl =
        """
          |set["abc", "cba"]
        """.stripMargin
      assertMatch(quickParse(container, asl)(testForm.form)){
        case collection: Set[Any] =>
          assert(collection.contains("abc"))
          assert(collection.contains("cba"))
      }
    }
  }
}
