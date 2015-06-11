package pl.pholda.malpompaaligxilo.form.field

import pl.pholda.malpompaaligxilo.i18n.Lang

abstract class EnumOptionOrdering extends Ordering[EnumOption]

object EnumOptionOrdering {
  lazy val byValue: EnumOptionOrdering = new EnumOptionOrdering {
    override def compare(x: EnumOption, y: EnumOption): Int =
      x.value compareTo y.value
  }

  def byCaption(implicit lang: Lang): EnumOptionOrdering = new EnumOptionOrdering {
    override def compare(x: EnumOption, y: EnumOption): Int =
    x.caption(lang) compareTo y.caption(lang)
  }
}