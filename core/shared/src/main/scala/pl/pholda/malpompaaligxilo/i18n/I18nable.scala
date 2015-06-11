package pl.pholda.malpompaaligxilo.i18n

import pl.pholda.malpompaaligxilo.util._

trait I18nable[T] {
  def apply(implicit lang: Lang): T

  def apply(n: Int)(implicit lang: Lang): T
}
