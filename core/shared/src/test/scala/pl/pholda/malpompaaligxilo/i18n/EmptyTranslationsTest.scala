package pl.pholda.malpompaaligxilo.i18n

import utest._

trait EmptyTranslationsTest extends TestSuite {

  val tests = TestSuite{
    't{
      assert(NoTranslations.t("singular").apply("pl")=="singular")
      assert(NoTranslations.t("singular").apply(2)("pl")=="singular")
    }
    'tp{
      assert(NoTranslations.tp("singular", "plural").apply("pl")=="singular")
      assert(NoTranslations.tp("singular", "plural").apply(2)("pl")=="singular")
    }
    'tc{
      assert(NoTranslations.tc("ctx", "singular").apply("pl")=="singular")
      assert(NoTranslations.tc("ctx", "singular").apply(2)("pl")=="singular")
    }
    'tcp{
      assert(NoTranslations.tcp("ctx", "singular", "plural").apply("pl")=="singular")
      assert(NoTranslations.tcp("ctx", "singular", "plural").apply(2)("pl")=="singular")
    }
  }

}
