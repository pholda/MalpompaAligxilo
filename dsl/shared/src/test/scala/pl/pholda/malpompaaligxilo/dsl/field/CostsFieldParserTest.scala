package pl.pholda.malpompaaligxilo.dsl.field

import pl.pholda.malpompaaligxilo.Context
import pl.pholda.malpompaaligxilo.dsl.{TestForm, ParserTestHelper}
import pl.pholda.malpompaaligxilo.dsl.parser.FieldTypeParser
import pl.pholda.malpompaaligxilo.form.field.calculateField.cost.CostDef.{ComplexCostDef, MultipleCostDef, SingleCostDef}
import pl.pholda.malpompaaligxilo.form.field.calculateField.cost.CostsField
import pl.pholda.malpompaaligxilo.i18n.{NoI18nString, TranslationProvider}
import pl.pholda.malpompaaligxilo.util.{Date, DateCompanion}
import utest._

trait CostsFieldParserTest extends TestSuite with ParserTestHelper[FieldTypeParser] {
  def testForm: TestForm


  override val parsers: FieldTypeParser = new FieldTypeParser {
    override implicit val context: Context = testForm.context
    override implicit val translationProvider: TranslationProvider = context.translationProvider
    override protected implicit val dateCompanion: DateCompanion = testForm.context.date
  }

  import parsers._

  val tests = TestSuite{
    'costDef{
      'singleCostDef{
        assertMatch(quickParse(costDef, """ single < "item" "desc" 10.3 { true } > """)(testForm.form)){
          case SingleCostDef("item", NoI18nString("desc"), 10.3, expression) =>
        }
      }
      'multipleCostDef{
        assertMatch(quickParse(costDef, """ multiple < "item" "desc" 10.1 { $"intField" } > """)(testForm.form)){
          case MultipleCostDef("item", NoI18nString("desc"), 10.1, expression) =>

        }
      }
      'complexCostDef{
        assertMatch(quickParse(costDef,
          """
            |complex {
            |  single < "item" "desc" 10.3 { true } >
            |  multiple < "item" "desc" 10.1 { $"intField" } >
            |}  """.stripMargin)(testForm.form)){
          case ComplexCostDef(
            SingleCostDef("item", NoI18nString("desc"), 10.3, _) ::
            MultipleCostDef("item", NoI18nString("desc"), 10.1, _) :: Nil
          ) =>
        }
      }
      'currencyFormat{
        assertMatch(quickParse(costsField,
          """
            |costs { single < "item" "desc" 10.3 { true } > } currencyFormat "%.2f :-)"
          """.stripMargin)(testForm.form)){
          case cf@CostsField(_, "%.2f :-)") =>
        }
      }
    }
  }
}
