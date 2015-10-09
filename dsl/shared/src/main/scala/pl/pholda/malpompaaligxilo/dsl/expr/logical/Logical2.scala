package pl.pholda.malpompaaligxilo.dsl.expr.logical

import pl.pholda.malpompaaligxilo.dsl.DslFormExpr
import pl.pholda.malpompaaligxilo.dsl.expr.math.Operation._
import pl.pholda.malpompaaligxilo.form.FormInstance

case class Logical2(operand1: DslFormExpr[_], operand2: DslFormExpr[_])
                   (operation: (Any, Any) => Boolean) extends DslFormExpr[Boolean] {
  override def apply(formInstance: FormInstance[_]): Boolean = {
    operation(expr2double(operand1)(formInstance), expr2double(operand2)(formInstance))
  }
}
