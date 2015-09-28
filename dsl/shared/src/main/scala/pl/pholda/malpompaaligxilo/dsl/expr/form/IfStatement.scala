package pl.pholda.malpompaaligxilo.dsl.expr.form

import pl.pholda.malpompaaligxilo.dsl.DslFormExpr
import pl.pholda.malpompaaligxilo.form.FormInstance

case class IfStatement(ifExpr: DslFormExpr[_], trueExpr: DslFormExpr[_], falseExpr: DslFormExpr[_])
  extends DslFormExpr[Any] {
  override def apply(formInstance: FormInstance): Any = {
    if (ifExpr(formInstance) == true) {
      trueExpr(formInstance)
    } else
      falseExpr(formInstance)
  }
}
