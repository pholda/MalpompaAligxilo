package pl.pholda.malpompaaligxilo.i18n

case class I18nString(values: Map[Lang, String]) extends I18nableString {
  override def apply(implicit lang: Lang): String = {
    values.apply(lang)
  }

  override def apply(n: Long)(implicit lang: Lang): String = apply(lang)
}

object I18nString {
  def apply(stringValues: (Lang, String)*): I18nString = I18nString(stringValues.toMap)
}
