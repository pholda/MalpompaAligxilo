package pl.pholda.malpompaaligxilo.dsl.expr.math

import pl.pholda.malpompaaligxilo.dsl.DslFormExpr
import pl.pholda.malpompaaligxilo.form.FormInstance

object Operation {
  def expr2double(expr: DslFormExpr[_])(implicit formInstance: FormInstance[_]): Double = {
    expr(formInstance) match {
      case d: Double => d
      case i: Int => i.toDouble
      case s: String => s.toDouble
      case true => 1
      case false => 0
      case x =>
        throw new Exception(s"numeric value expected, found $x")
    }
  }
}
