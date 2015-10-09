package pl.pholda.malpompaaligxilo.dsl.expr.form

import pl.pholda.malpompaaligxilo.dsl.DslFormExpr
import pl.pholda.malpompaaligxilo.form.FormInstance

case class FieldValueOpt(fieldName: String) extends DslFormExpr[Option[Any]] {
  override def apply(formInstance: FormInstance[_]): Option[Any] =
    formInstance.fieldsByName(fieldName).value(formInstance)
}
