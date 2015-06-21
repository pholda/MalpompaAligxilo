package pl.pholda.malpompaaligxilo.i18n

trait I18nableString extends I18nable[String] {
  def +(r: I18nableString): I18nableString = {
    val l = this
    new I18nableString {
      override def apply(implicit lang: Lang): String =
        l(lang) + r(lang)

      override def apply(n: Long)(implicit lang: Lang): String =
        l(n)(lang) + r(n)(lang)
    }
  }
}
