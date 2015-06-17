package pl.pholda.malpompaaligxilo.i18n

trait I18nable[T] {
  def apply(implicit lang: Lang): T

  def apply(n: Long)(implicit lang: Lang): T
}
