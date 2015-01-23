package pl.pej.malpompaaligxilo.util

case class NoI18nString(str: String) extends I18nable[String] {
  override def apply(lang: Lang): String = str
}
