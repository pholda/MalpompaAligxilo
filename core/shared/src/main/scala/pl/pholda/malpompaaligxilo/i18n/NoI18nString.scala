package pl.pholda.malpompaaligxilo.i18n

import pl.pholda.malpompaaligxilo.util._

case class NoI18nString(str: String) extends I18nableString {
  override def apply(implicit lang: Lang): String = str

  override def apply(n: Int)(implicit lang: Lang): String = str
}
