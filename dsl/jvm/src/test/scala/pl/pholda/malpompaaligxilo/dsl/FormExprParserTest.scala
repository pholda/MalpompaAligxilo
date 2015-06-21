package pl.pholda.malpompaaligxilo.dsl

import pl.pholda.malpompaaligxilo.dsl.DslFormExpr.StringConcat
import pl.pholda.malpompaaligxilo.dsl.parser.FormExprParser
import pl.pholda.malpompaaligxilo.i18n.{I18nableString, I18n}
import utest._

object FormExprParserTest extends TestSuite with ParserTestHelper[FormExprParser] {

  override val parsers = new FormExprParser {
    override implicit val i18n: I18n = TestForm.i18n
  }

  val tests = TestSuite{

    import TestForm.form
    import parsers._

    'stringFieldValue{
      assert(quickParse(fieldValue, "value \"a\"")(form) == "value-a")
      assert(quickParse(fieldValue, "$ \"a\"")(form) == "value-a")
    }

    'compareFieldValue{
      assert(quickParse(compare, """ (value "a" = "value-a") """)(form))
      assert(quickParse(compare, """ ("value-a" = value "a") """)(form))
    }

    'stringConcat{
      'literals{
        val expr = quickParse[StringConcat](stringConcat, """ ( "abc" + "123" + "!@#" ) """)
        expr(form) match {
          case str: I18nableString =>
            assert(str("en")=="abc123!@#")
        }
      }
      'literal_fieldValue{
        val expr = quickParse[StringConcat](stringConcat,
          """ ("abc" + $"a") """
        )
        expr(form) match {
          case str: I18nableString =>
            assert(str("en")=="abcvalue-a")
        }
      }
    }
  }
}
