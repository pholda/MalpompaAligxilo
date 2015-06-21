package pl.pholda.malpompaaligxilo.dsl

import pl.pholda.malpompaaligxilo.dsl.parser.FormSpecificationParserTrait
import pl.pholda.malpompaaligxilo.form.field.StringField
import pl.pholda.malpompaaligxilo.i18n.I18n
import utest._

object FormSpecificationParserTest extends TestSuite with ParserTestHelper[FormSpecificationParserTrait] {

  override val parsers = new FormSpecificationParserTrait{
    override implicit val i18n: I18n = TestForm.i18n
  }

  import TestForm.form
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
      assert(f.visible(form))
      assert(f.name == "name")
      assert(f.`type` == StringField())
    }
//    'calculable{
//      'literal{
//        val r = quickParse(calculable, "calculable { true }")
//        println(r)
//      }
//    }
  }
}
