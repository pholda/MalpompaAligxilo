package pl.pholda.malpompaaligxilo.dsl

import pl.pholda.malpompaaligxilo.dsl.parser.I18nParser
import pl.pholda.malpompaaligxilo.i18n.I18n
import utest._

object I18nParserTest extends TestSuite with ParserTestHelper[I18nParser] {

  override val parsers: I18nParser = new I18nParser {
    override implicit val i18n: I18n = TestForm.i18n
  }
  import parsers._

  val tests = TestSuite{
    'i18nString{
      'parsing{
        val a = quickParse(i18nString, """ @ "Name" """)

        assert(a("en") == "Name")
        assert(a("pl") == "Imię")
      }
//      'concat{
//        val a = quickParse(i18nStringRef, """ @ "Name" """)
//        val b = quickParse(i18nStringRef, """ @ "Surname" """)
//
//        assert((a+b)("pl") == "ImięNazwisko")
//      }
    }
  }
}
