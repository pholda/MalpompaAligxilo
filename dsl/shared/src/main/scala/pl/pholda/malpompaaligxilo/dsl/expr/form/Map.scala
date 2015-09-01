package pl.pholda.malpompaaligxilo.dsl.expr.form

import pl.pholda.malpompaaligxilo.dsl.DslFormExpr
import pl.pholda.malpompaaligxilo.form.FormInstance

case class Map(value: DslFormExpr[Any], cases: MapCase*) extends DslFormExpr[Any] {
  override def apply(formInstance: FormInstance): Any = {
    val computedValue = value(formInstance)
    cases.collectFirst{
      case MapCase(pattern, expression) if pattern(computedValue, formInstance) =>
        expression.expr(formInstance)
    }.get
  }
}
