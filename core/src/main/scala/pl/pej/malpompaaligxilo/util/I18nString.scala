package pl.pej.malpompaaligxilo.util

case class I18nString(values: Map[Lang, String]) extends I18nable[String] {
  override def apply(implicit lang: Lang): String = values.apply(lang)
}

object I18nString {
  def apply(stringValues: (Lang, String)*): I18nString = I18nString(stringValues.toMap)
}