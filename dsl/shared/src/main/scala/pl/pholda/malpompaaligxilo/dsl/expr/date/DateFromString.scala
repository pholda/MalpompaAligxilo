package pl.pholda.malpompaaligxilo.dsl.expr.date

import pl.pholda.malpompaaligxilo.dsl.DslFormExpr
import pl.pholda.malpompaaligxilo.form.FormInstance
import pl.pholda.malpompaaligxilo.util.Date

case class DateFromString(string: DslFormExpr[Any]) extends DslFormExpr[Date] {
  override def apply(formInstance: FormInstance): Date = {
    val stringDate = string(formInstance) match {
      case Some(s: String) => s
      case s: String => s
      case x => throw new Exception(s"cannot execute dateFromString on not string! (passed value: $x")
    }
    formInstance.dates.fromString(stringDate)
  }
}