package pl.pholda.malpompaaligxilo.i18n

object EmptyI18n extends I18n {
  override def t(singular: String): I18nableString = NoI18nString(singular)

  override def tp(singular: String, plural: String): I18nableString = NoI18nString(singular)

  override def tc(ctx: String, singular: String): I18nableString = NoI18nString(singular)

  override def tcp(ctx: String, singular: String, plural: String): I18nableString = NoI18nString(singular)
}
