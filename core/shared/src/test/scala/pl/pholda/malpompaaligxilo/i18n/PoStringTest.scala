package pl.pholda.malpompaaligxilo.i18n

import utest._

trait PoStringTest extends TestSuite {
  def poSet: PoSet

  val tests = TestSuite{
    'polishPlurals{
      'simple{
        val str = PoString(poSet)("file", plural = Some("files"))
        assert(str(1)("pl") == "plik")
        assert(str(3)("pl") == "pliki")
        assert(str(5)("pl") == "plików")
      }
    }
    'englishPlurals{
      val str = PoString(poSet)("file", plural = Some("files"))
      assert(str(1)("en") == "file")
      assert(str(3)("en") == "files")
      assert(str(5)("en") == "files")
    }
  }
}
