package pl.pholda.malpompaaligxilo.dsl

import pl.pholda.malpompaaligxilo.dsl.parser.I18nParser
import pl.pholda.malpompaaligxilo.i18n.TranslationProvider
import utest._

trait I18nParserTest extends TestSuite with ParserTestHelper[I18nParser] {
  def testForm: TestForm

  override val parsers: I18nParser = new I18nParser {
    override implicit val translationProvider: TranslationProvider = testForm.context.translationProvider
  }
  import parsers._

  val tests = TestSuite{
    'i18nString{
      'reference{
        val str = quickParse(i18nString, """ @ "Name" """)

        assert(str("en") == "Name")
        assert(str("pl") == "Imię")
      }
      'literal{
        val str = quickParse(i18nString, """ "abc" """)
        assert(str("en") == "abc")
        assert(str("pl") == "abc")
      }
    }
  }
}
