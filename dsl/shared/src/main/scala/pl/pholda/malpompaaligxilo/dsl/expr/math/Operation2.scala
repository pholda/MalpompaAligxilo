package pl.pholda.malpompaaligxilo.dsl.expr.math

import pl.pholda.malpompaaligxilo.dsl.DslFormExpr
import pl.pholda.malpompaaligxilo.form.FormInstance
import pl.pholda.malpompaaligxilo.dsl.expr.math.Operation._

case class Operation2(operand1: DslFormExpr[_], operand2: DslFormExpr[_])
                     (operation: (Double, Double) => Double) extends DslFormExpr[Double] {
  override def apply(formInstance: FormInstance[_]): Double = {
    operation(expr2double(operand1)(formInstance), expr2double(operand2)(formInstance))
  }
}
