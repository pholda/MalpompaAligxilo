package pl.pholda.malpompaaligxilo.i18n

trait I18nableString {
  def apply(implicit lang: Lang): String

  def apply(n: Long)(implicit lang: Lang): String

  def +(r: I18nableString): I18nableString = {
    val l = this
    new I18nableString {
      def apply(implicit lang: Lang): String =
        l(lang) + r(lang)

      def apply(n: Long)(implicit lang: Lang): String =
        l(n)(lang) + r(n)(lang)
    }
  }
}
