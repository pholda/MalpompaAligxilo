package pl.pholda.malpompaaligxilo.dsl

import pl.pholda.malpompaaligxilo.Context
import pl.pholda.malpompaaligxilo.dsl.parser.FormSpecificationParserTrait
import pl.pholda.malpompaaligxilo.form.field.StringField
import pl.pholda.malpompaaligxilo.i18n.TranslationProvider
import pl.pholda.malpompaaligxilo.util.DateCompanion
import utest._

trait FormSpecificationParserTest extends TestSuite with ParserTestHelper[FormSpecificationParserTrait] {
  def testForm: TestForm

  override val parsers = new FormSpecificationParserTrait{
    override implicit val context: Context = testForm.context
    override implicit val translationProvider: TranslationProvider = context.translationProvider
    override implicit val dateCompanion: DateCompanion = testForm.context.date
  }
  import parsers._

  val tests = TestSuite{
//    'fieldCaption1{
//      val input =
//        """
//          |@ "aaa"
//        """.stripMargin
//
//      assert(quickParse(i18nStringRef, input) == SingularI18nStringRef("aaa"))
//    }
    'field{
      val input =
        """<
          |name = "name", type = string, caption = @ "name", visible = { ($"a" = "value-a") }
          |>
        """.stripMargin
      val f = quickParse(field, input)
      assert(f.visible(testForm.form))
      assert(f.name == "name")
      assert(f.`type` == StringField())
    }
//    'calculable{
//      'literal{
//        val r = quickParse(calculable, "calculable { true }")
//      }
//    }
  }
}
