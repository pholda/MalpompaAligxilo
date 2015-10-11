package pl.pholda.malpompaaligxilo.dsl

import pl.pholda.malpompaaligxilo.dsl.parser.expr.FormExprParser
import pl.pholda.malpompaaligxilo.form.FormInstance
import pl.pholda.malpompaaligxilo.i18n.TranslationProvider
import pl.pholda.malpompaaligxilo.util.DateCompanion
import utest._

trait LogicExprParserTest extends TestSuite with ParserTestHelper[FormExprParser] {
  def testForm: TestForm

  override val parsers = new FormExprParser {
    override implicit val translationProvider: TranslationProvider = testForm.context.translationProvider
    override implicit val dateCompanion: DateCompanion = testForm.context.date
  }


  val tests = TestSuite {

    def form: FormInstance[_] = testForm.form

    'negation{
      val expr = """ { ! { true } } """
      assertMatch(quickParse(parsers.expr, expr)(testForm.form)){
        case false =>
      }
    }
//    'greater{
//      assertMatch(quickParse(parsers.logicExpr, "5 > 4")(testForm.form)){
//        case true =>
//      }
//      assertMatch(quickParse(parsers.logicExpr, "5 > 5")(testForm.form)){
//        case false =>
//      }
//    }
  }

}
