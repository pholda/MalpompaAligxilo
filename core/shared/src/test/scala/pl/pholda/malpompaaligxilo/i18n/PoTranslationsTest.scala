package pl.pholda.malpompaaligxilo.i18n

import utest._

trait PoTranslationsTest extends TestSuite {

  def poTranslations: PoTranslations

  val tests = TestSuite{
    'singular{
      assert(poTranslations.t("file")(lang = "pl") == "plik")
    }
    'plural{
      assert(poTranslations.tp("file", "files")(5)("pl") == "plików")
    }
  }

}
