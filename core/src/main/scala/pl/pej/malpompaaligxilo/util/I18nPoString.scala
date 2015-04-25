package pl.pej.malpompaaligxilo.util

import scala.util.Try

class I18nPoString(values: Map[Lang, Int => String]) extends I18nableString {
  //TODO add context, plurar etc
  override def apply(implicit lang: Lang): String = Try{values(lang)(0)}.getOrElse(":(")

  override def apply(n: Int)(implicit lang: Lang): String = values(lang)(n)
}
