package pl.pholda.malpompaaligxilo.dsl.expr.logical

import pl.pholda.malpompaaligxilo.dsl.DslFormExpr
import pl.pholda.malpompaaligxilo.form.FormInstance

case class Negation(expr: DslFormExpr[_]) extends DslFormExpr[Any] {
  override def apply(formInstance: FormInstance[_]): Any = {
    expr(formInstance) match {
      case b: Boolean => !b
      case x => x
    }
  }
}
