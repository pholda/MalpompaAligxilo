package pl.pej.malpompaligxilo.util

trait I18nable[T] {
  def apply(lang: Lang): T
}
