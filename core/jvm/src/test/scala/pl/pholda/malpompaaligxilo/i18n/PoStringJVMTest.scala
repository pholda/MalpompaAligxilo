package pl.pholda.malpompaaligxilo.i18n

import pl.pholda.malpompaaligxilo.i18n.{I18nJVM, PoSet}

object PoStringJVMTest extends PoStringTest {
  override val poSet: PoSet = I18nJVM.fromResources(getClass,
    "en" -> "/en.po",
    "pl" -> "/pl.po"
  ).poSet
}
