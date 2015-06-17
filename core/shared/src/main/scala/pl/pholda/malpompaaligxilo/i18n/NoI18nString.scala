package pl.pholda.malpompaaligxilo.i18n

case class NoI18nString(str: String) extends I18nableString {
  override def apply(implicit lang: Lang): String = str

  override def apply(n: Long)(implicit lang: Lang): String = str
}
