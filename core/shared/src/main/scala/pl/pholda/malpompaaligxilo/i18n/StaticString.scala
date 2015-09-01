package pl.pholda.malpompaaligxilo.i18n

case class StaticString(values: Map[Lang, String]) extends I18nableString {
  override def apply(implicit lang: Lang): String = {
    values.apply(lang)
  }

  override def apply(n: Long)(implicit lang: Lang): String = apply(lang)
}

object StaticString {
  def apply(stringValues: (Lang, String)*): StaticString = StaticString(stringValues.toMap)
}
