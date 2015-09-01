package pl.pholda.malpompaaligxilo.dsl.expr.date

import pl.pholda.malpompaaligxilo.dsl.DslFormExpr
import pl.pholda.malpompaaligxilo.form.FormInstance
import pl.pholda.malpompaaligxilo.util.Date

case class DayOfWeek(inner: DslFormExpr[Any]) extends DslFormExpr[Int] {
  override def apply(formInstance: FormInstance): Int = {
    inner(formInstance) match {
      case date: Date =>
        date.getDayOfWeek
    }
  }
}
