package pl.pholda.malpompaaligxilo.dsl.expr.form

import pl.pholda.malpompaaligxilo.dsl.DslFormExpr
import pl.pholda.malpompaaligxilo.form.FormInstance

case class FieldValue(fieldName: String) extends DslFormExpr[Any] {
  override def apply(form: FormInstance): Any =
    form.fieldsByName(fieldName).value(form).get
}
