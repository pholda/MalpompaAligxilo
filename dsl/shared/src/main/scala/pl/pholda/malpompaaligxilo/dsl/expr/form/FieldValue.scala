package pl.pholda.malpompaaligxilo.dsl.expr.form

import pl.pholda.malpompaaligxilo.dsl.DslFormExpr
import pl.pholda.malpompaaligxilo.form.FormInstance

case class FieldValue(fieldName: String) extends DslFormExpr[Any] {
  override def apply(formInstance: FormInstance[_]): Any =
    formInstance.fieldsByName(fieldName).value(formInstance).get
}
