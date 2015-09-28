package pl.pholda.malpompaaligxilo.dsl

import pl.pholda.malpompaaligxilo.Context
import pl.pholda.malpompaaligxilo.dsl.parser.FieldTypeParser
import pl.pholda.malpompaaligxilo.form.field._
import pl.pholda.malpompaaligxilo.i18n.TranslationProvider
import pl.pholda.malpompaaligxilo.util.DateCompanion
import utest._

trait FieldTypeParserTest extends TestSuite with ParserTestHelper[FieldTypeParser] {
  def testForm: TestForm

  override val parsers: FieldTypeParser = new FieldTypeParser {
    override implicit val context: Context = testForm.context
    override implicit val translationProvider: TranslationProvider = context.translationProvider
    override protected implicit val dateCompanion: DateCompanion = testForm.context.date
  }

  import parsers._

  val tests = TestSuite{
    'string{
      'simple{
        assertMatch(quickParse(fieldType, """ type = string """)){
          case StringField(false, None) =>
        }
      }
      'multiline {
        assertMatch(quickParse(fieldType, """ type = string multiline """)) {
          case StringField(true, None) =>
        }
      }
      'defaultValue{
        assertMatch(quickParse(fieldType, """ type = string default ( "abc123" ) """)) {
          case StringField(false, Some("abc123")) =>
        }
      }
    }
    'email{
      assertMatch(quickParse(fieldType, """ type = email """)){
        case EmailField =>
      }
    }
    'int{
      'simple{
        assertMatch(quickParse(fieldType, """ type = int """)){
          case IntField(None, None, None) =>
        }
      }
      'min{
        assertMatch(quickParse(fieldType, """ type = int min 5""")){
          case IntField(Some(5), None, None) =>
        }
      }
      'max{
        assertMatch(quickParse(fieldType, """ type = int max 5""")){
          case IntField(None, Some(5), None) =>
        }
      }
      'step{
        assertMatch(quickParse(fieldType, """ type = int step 5""")){
          case IntField(None, None, Some(5)) =>
        }
      }
    }
    'computed{
      assertMatch(quickParse(fieldType, """ type = computed { true } """)){
        case CustomComputeField(expr) =>
      }
    }
    'checkbox{
      'simple {
        assertMatch(quickParse(fieldType, """ type = checkbox """)) {
          case CheckboxField(_) =>
        }
      }
      'default {
        assertMatch(quickParse(fieldType, """ type = checkbox default (true) """)) {
          case CheckboxField(true) =>
        }
        assertMatch(quickParse(fieldType, """ type = checkbox default (false) """)) {
          case CheckboxField(false) =>
        }
      }
    }
    'date{
      'simple{
        assertMatch(quickParse(fieldType, """ type = date """)) {
          case DateField(_, _, _) =>
            //TODO
        }
      }
    }
    'select{
      'simple{
        assertMatch(quickParse(fieldType, """ type = select { < "low" @"low" > < "high" @"high" >} """)){
          case SelectField(List(EnumOption("low", _), EnumOption("high", _)), 1, None, None) =>
        }
      }
      'simpleComma{
        assertMatch(quickParse(fieldType, """ type = select { < "low" @"low" >, < "high" @"high" >} """)){
          case SelectField(List(EnumOption("low", _), EnumOption("high", _)), 1, None, None) =>
        }
      }
      'size{
        assertMatch(quickParse(fieldType, """ type = select size 2 { < "low" @"low" >, < "high" @"high" >} """)){
          case SelectField(_, 2, None, None) =>
        }
      }
      'notSelected{
        assertMatch(quickParse(fieldType,
          """ type = select {
            |< "low" @"low" >
            |} notSelected < "nothing" @"please choose option.." >""".stripMargin)){
          case SelectField(_, _, Some(EnumOption("nothing", _)), None) =>
        }
      }
      //TODO ordering
    }
    'checkboxTable{
      'twoColsTwoRows{
        assertMatch(quickParse(fieldType,
        """
          |type = checkboxTable
          |rows { <"a" "a"> <"b" "b"> }
          |cols { <"1" "1">,< "2" "2"> }
        """.stripMargin)){
          case CheckboxTableField(
            List(CheckboxTableRow("a", _), CheckboxTableRow("b", _)),
            List(CheckboxTableCol("1", _), CheckboxTableCol("2", _)),
            _,
            _
          ) =>
        }
      }
      'twoColsTwoRowsDisabledA1B2{
        assertMatch(quickParse(fieldType,
          """
            |type = checkboxTable
            |rows { <"a" "a"> <"b" "b"> }
            |cols { <"1" "1">,< "2" "2"> }
            |disabled { <"a" "1">, <"b" "2"> }
          """.stripMargin)){
          case CheckboxTableField(_, _, List(("a", "1"), ("b", "2")), _) =>
        }
      }
      'default{
        assertMatch(quickParse(fieldType,
          """
            |type = checkboxTable
            |rows { <"a" "a"> <"b" "b"> }
            |cols { <"1" "1">,< "2" "2"> }
            |default ( true )
          """.stripMargin)){
          case CheckboxTableField(
          List(CheckboxTableRow("a", _), CheckboxTableRow("b", _)),
          List(CheckboxTableCol("1", _), CheckboxTableCol("2", _)),
          _,
          true
          ) =>
        }
      }
    }
  }
}
