package pl.pholda.malpompaaligxilo.i18n

import utest._

trait StaticStringTest extends TestSuite {
  val tests = TestSuite{
    'simple{
      val s = StaticString(
        "pl" -> "polski",
        "en" -> "english"
      )
      assert(s("pl") == "polski")
      assert(s(5)("pl") == "polski")
      assert(s("en") == "english")
      assert(s(5)("en") == "english")
    }
  }
}
