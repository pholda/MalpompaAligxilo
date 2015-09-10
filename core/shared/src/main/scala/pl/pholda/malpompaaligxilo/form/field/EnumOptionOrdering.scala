package pl.pholda.malpompaaligxilo.form.field

import java.text.Collator
import java.util.Locale

import pl.pholda.malpompaaligxilo.i18n.Lang

abstract class EnumOptionOrdering {
  def ordering(selectField: SelectField)(implicit lang: Lang): ((Int, EnumOption), (Int, EnumOption)) => Boolean
}

object EnumOptionOrdering {
  case object byValue extends EnumOptionOrdering{
    override def ordering(selectField: SelectField)(implicit lang: Lang) = {
      case ((_, a: EnumOption), (_, b: EnumOption)) =>
        a.value < b.value
    }
  }

  case object byCaption extends EnumOptionOrdering {
    override def ordering(selectField: SelectField)(implicit lang: Lang) = {
      case ((_, a: EnumOption), (_, b: EnumOption)) =>
        val collator = Collator.getInstance(Locale.forLanguageTag(lang))
        collator.compare(a.caption(lang), b.caption(lang)) < 0
    }
  }
}
