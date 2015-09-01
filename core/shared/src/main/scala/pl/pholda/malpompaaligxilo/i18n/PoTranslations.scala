package pl.pholda.malpompaaligxilo.i18n

class PoTranslations(val poSet: PoSet) extends TranslationProvider {
  override def t(singular: String): I18nableString =
    PoString(poSet)(singular)

  override def tc(ctx: String, singular: String): I18nableString =
    PoString(poSet)(singular, Some(ctx))

  override def tp(singular: String, plural: String): I18nableString =
    PoString(poSet)(singular, plural = Some(plural))

  override def tcp(ctx: String, singular: String, plural: String): I18nableString =
    PoString(poSet)(singular, Some(ctx), Some(plural))
}
