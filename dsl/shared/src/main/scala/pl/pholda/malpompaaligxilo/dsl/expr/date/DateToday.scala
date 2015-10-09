package pl.pholda.malpompaaligxilo.dsl.expr.date

import pl.pholda.malpompaaligxilo.dsl.DslFormExpr
import pl.pholda.malpompaaligxilo.form.FormInstance
import pl.pholda.malpompaaligxilo.util.Date

case object DateToday extends DslFormExpr[Date] {
  override def apply(formInstance: FormInstance[_]): Date = {
    formInstance.dates.now
  }
}