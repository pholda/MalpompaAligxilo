package pl.pholda.malpompaaligxilo.i18n

import utest._

trait NoI18nStringTest extends TestSuite {
  val tests = TestSuite{
    'simple{
      val noI18nString = NoI18nString("abc123")
      assert(noI18nString(1)("en") == "abc123")
      assert(noI18nString(1)("pl") == "abc123")
      assert(noI18nString(5)("en") == "abc123")
      assert(noI18nString(5)("pl") == "abc123")
    }
  }
}
