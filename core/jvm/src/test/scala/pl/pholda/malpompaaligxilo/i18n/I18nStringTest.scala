package pl.pholda.malpompaaligxilo.i18n

import utest._

object I18nStringTest extends TestSuite {
  val tests = TestSuite{
    'staticValues{
      val i18nStr = I18nStaticString(
        "en" -> "in english",
        "pl" -> "po polsku"
      )
      assert(i18nStr("en") == "in english")
      assert(i18nStr("pl") == "po polsku")
      assert(i18nStr(1)("en") == "in english")
      assert(i18nStr(1)("pl") == "po polsku")
      assert(i18nStr(5)("en") == "in english")
      assert(i18nStr(5)("pl") == "po polsku")
    }
    '+{
      val a = I18nStaticString(
        "en" -> "aaa"
      )
      val b = I18nStaticString(
        "en" -> "bbb"
      )
      val sum = a+b
      assert(sum("en") == "aaabbb")
    }
  }

}
