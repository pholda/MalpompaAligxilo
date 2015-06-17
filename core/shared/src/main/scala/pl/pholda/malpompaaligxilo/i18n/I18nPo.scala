package pl.pholda.malpompaaligxilo.i18n

class I18nPo(val poSet: PoSet) extends I18n {
  override def t(singular: String): I18nableString =
    PoI18nString(poSet)(singular)

  override def tc(ctx: String, singular: String): I18nableString =
    PoI18nString(poSet)(singular, Some(ctx))

  override def tp(singular: String, plural: String): I18nableString =
    PoI18nString(poSet)(singular, plural = Some(plural))

  override def tcp(ctx: String, singular: String, plural: String): I18nableString =
    PoI18nString(poSet)(singular, Some(ctx), Some(plural))
}
