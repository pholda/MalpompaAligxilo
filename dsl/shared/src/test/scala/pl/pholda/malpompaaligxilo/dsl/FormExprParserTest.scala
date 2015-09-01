package pl.pholda.malpompaaligxilo.dsl

import pl.pholda.malpompaaligxilo.dsl.expr.form.StringConcat
import pl.pholda.malpompaaligxilo.dsl.parser.expr.FormExprParser
import pl.pholda.malpompaaligxilo.form.field.EnumOption
import pl.pholda.malpompaaligxilo.i18n.{NoI18nString, I18nableString, TranslationProvider}
import pl.pholda.malpompaaligxilo.util.DateCompanion
import utest._

trait FormExprParserTest extends TestSuite with ParserTestHelper[FormExprParser] {
  def testForm: TestForm

  override val parsers = new FormExprParser {
    override implicit val translationProvider: TranslationProvider = testForm.context.translationProvider
    override implicit val dateCompanion: DateCompanion = testForm.context.date
  }

  val tests = TestSuite{

    def form = testForm.form
    import parsers._

    'fieldValue{
      'string{
        assertMatch(quickParse(innerExpr, "value \"a\"")(form)) {
          case "value-a" =>
        }
        assertMatch(quickParse(innerExpr, "$ \"a\"")(form)){
          case "value-a" =>
        }
      }
      'int{
        assertMatch(quickParse(innerExpr, """ $"intField" """)(form)){
          case 123 =>
        }
      }
      'select{
        assertMatch(quickParse(innerExpr, """ $"selectField" """)(form)){
          case EnumOption("2", NoI18nString("second")) =>
        }
      }
    }

    'compareFieldValue{
      assertMatch(quickParse(innerExpr, """ (value "a" = "value-a") """)(form)){
        case true =>
      }
      assertMatch(quickParse(innerExpr, """ ("value-a" = value "a") """)(form)){
        case true =>
      }
    }

    'stringConcat{
      'literals{
        val expr = quickParse(innerExpr, """ ( "abc" + "123" + "!@#" ) """)
        expr(form) match {
          case str: I18nableString =>
            assert(str("en")=="abc123!@#")
        }
      }
      'literal_fieldValue{
        val expr = quickParse(innerExpr,
          """ ("abc" + $"a") """
        )
        expr(form) match {
          case str: I18nableString =>
            assert(str("en")=="abcvalue-a")
        }
      }
    }
    'mapValue{
      'simplePattern{
        val asl =
          """
            |mapValue( compareDates (date("2014-06-06") < date("2014-06-07")) ) {
            | true => "1"
            | false => "0"
            |}
          """.stripMargin
        assertMatch(quickParse(innerExpr, asl)(form)){
          case "1" =>
        }
      }

      'default{
        val asl =
          """
            |mapValue ( 5 ) {
            | true => 0
            | false => 1
            | default => "default!"
            |}
          """.stripMargin
        assertMatch(quickParse(innerExpr, asl)(form)){
          case "default!" =>
        }
      }
    }
    'enumValue{
      val asl =
        """
          | enumValue($"selectField")
        """.stripMargin
      assertMatch(quickParse(innerExpr, asl)(form)){
        case "2" =>
      }
    }
    'enumCaption{
      val asl =
      """
        | enumCaption($"selectField")
      """.stripMargin
      assertMatch(quickParse(innerExpr, asl)(form)){
        case NoI18nString("second") =>
      }
    }
  }
}
