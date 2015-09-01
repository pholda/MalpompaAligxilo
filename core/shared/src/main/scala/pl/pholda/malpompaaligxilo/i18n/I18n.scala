package pl.pholda.malpompaaligxilo.i18n

object I18n {
  def t(singular: String)(implicit translationProvider: TranslationProvider, lang: Lang): String =
    translationProvider.t(singular)(lang)

  def tc(ctx: String, singular: String)(implicit translationProvider: TranslationProvider, lang: Lang): String =
    translationProvider.tc(ctx, singular)(lang)

  def tp(singular: String, plural: String)(implicit translationProvider: TranslationProvider, lang: Lang): String =
    translationProvider.tp(singular, plural)(lang)

  def tcp(ctx: String, singular: String, plural: String)(implicit translationProvider: TranslationProvider, lang: Lang): String =
    translationProvider.tcp(ctx, singular, plural)(lang)
}
