package pl.pholda.malpompaaligxilo.i18n

object PoTranslationsJVMTest extends PoTranslationsTest {
  override val poTranslations: PoTranslations = I18nJVM.fromResources(getClass,
    "en" -> "/en.po",
    "pl" -> "/pl.po"
  )
}
