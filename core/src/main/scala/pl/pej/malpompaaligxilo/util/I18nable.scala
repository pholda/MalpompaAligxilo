package pl.pej.malpompaaligxilo.util

trait I18nable[T] {
  def apply(implicit lang: Lang): T

  def apply(n: Int)(implicit lang: Lang): T
}
