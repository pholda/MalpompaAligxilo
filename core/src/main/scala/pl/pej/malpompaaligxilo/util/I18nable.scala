package pl.pej.malpompaaligxilo.util

trait I18nable[T] {
  def apply(lang: Lang): T
}
