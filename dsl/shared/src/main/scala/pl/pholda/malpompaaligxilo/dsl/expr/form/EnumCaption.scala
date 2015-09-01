package pl.pholda.malpompaaligxilo.dsl.expr.form

import pl.pholda.malpompaaligxilo.dsl.DslFormExpr
import pl.pholda.malpompaaligxilo.form.FormInstance
import pl.pholda.malpompaaligxilo.form.field.EnumOption
import pl.pholda.malpompaaligxilo.i18n.I18nString

case class EnumCaption(inner: DslFormExpr[_]) extends DslFormExpr[I18nString] {
  override def apply(formInstance: FormInstance): I18nString = {
    inner(formInstance) match {
      case EnumOption(_, caption) => caption
      case x => throw new IllegalArgumentException(s"Unable to take caption of $x")
    }
  }
}
