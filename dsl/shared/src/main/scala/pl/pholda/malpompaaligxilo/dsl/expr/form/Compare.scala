package pl.pholda.malpompaaligxilo.dsl.expr.form

import pl.pholda.malpompaaligxilo.dsl.DslFormExpr
import pl.pholda.malpompaaligxilo.form.FormInstance

case class Compare(a: DslFormExpr[_], b: DslFormExpr[_]) extends DslFormExpr[Boolean] {
  override def apply(formInstance: FormInstance[_]): Boolean = a(formInstance) == b(formInstance)
}
