package pl.pholda.malpompaaligxilo.i18n

case class PoI18nString(poSet: PoSet)(
    singular: String,
    context: Option[String] = None,
    plural: Option[String] = None
  ) extends I18nableString {
  def apply(implicit lang: Lang): String = poSet.translations(lang).t(singular)

  def apply(n: Long)(implicit lang: Lang): String = poSet.translations(lang)
    .t(singular, context, plural, Some(n))
}
