package pl.pholda.malpompaaligxilo.i18n

import utest._

trait I18nableStringTest extends TestSuite {
  val tests = TestSuite{
    'plus{
      val s1 = new I18nableString{
        override def apply(implicit lang: Lang): String = "one"

        override def apply(n: Long)(implicit lang: Lang): String = "one"
      }
      val s2 = new I18nableString{
        override def apply(implicit lang: Lang): String = "two"

        override def apply(n: Long)(implicit lang: Lang): String = "two"
      }
      val sumString = s1 + s2
      assert(sumString("en") == "onetwo")
      assert(sumString(5)("en") == "onetwo")
    }
  }
}
