package pl.pholda.malpompaaligxilo.dsl

import pl.pholda.malpompaaligxilo.dsl.parser.expr.FormExprParser
import pl.pholda.malpompaaligxilo.form.FormInstance
import pl.pholda.malpompaaligxilo.i18n.TranslationProvider
import pl.pholda.malpompaaligxilo.util.DateCompanion
import utest._

trait CheckboxTableExprParserTest extends TestSuite with ParserTestHelper[FormExprParser] {
  def testForm: TestForm

  override val parsers = new FormExprParser {
    override implicit val translationProvider: TranslationProvider = testForm.context.translationProvider
    override implicit val dateCompanion: DateCompanion = testForm.context.date
  }


  val tests = TestSuite {

    def form: FormInstance[_] = testForm.form
    import parsers._

    'selectedInTotal{
      val expr =
        """
          |selectedInTotal($"cbt")
          |
        """.stripMargin
    }
  }

}
