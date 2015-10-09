package pl.pholda.malpompaaligxilo.dsl.expr.form

import pl.pholda.malpompaaligxilo.dsl.DslFormExpr
import pl.pholda.malpompaaligxilo.form.FormInstance
import pl.pholda.malpompaaligxilo.form.field.EnumOption
import pl.pholda.malpompaaligxilo.i18n.I18nString

case class EnumValue(inner: DslFormExpr[Any]) extends DslFormExpr[String] {
  override def apply(formInstance: FormInstance[_]): String = {
    inner(formInstance) match {
      case EnumOption(value, _) => value
      case x => throw new IllegalArgumentException(s"Unable to take value of $x")
    }
  }
}
