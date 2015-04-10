package pl.pej.malpompaaligxilo.util

case class NoI18nString(str: String) extends I18nable[String] {
  override def apply(implicit lang: Lang): String = str
}
