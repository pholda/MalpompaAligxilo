package pl.pholda.malpompaaligxilo.dsl.expr.date

import pl.pholda.malpompaaligxilo.dsl.DslFormExpr
import pl.pholda.malpompaaligxilo.form.FormInstance
import pl.pholda.malpompaaligxilo.util.Date

case class DateDiff(unit: String, from: DslFormExpr[Any], to: DslFormExpr[Any]) extends DslFormExpr[Int] {
  override def apply(formInstance: FormInstance[_]): Int = {
    val fromDate = from(formInstance) match {
      case d: Date => d
      case x => throw new Exception(s"date was expected, found: $x")
    }
    val toDate = to(formInstance) match {
      case d: Date => d
      case x => throw new Exception(s"date was expected, found: $x")
    }
    unit match {
      case "years" =>
        fromDate yearsTo toDate
      case "months" =>
        fromDate monthsTo toDate
      case "days" =>
        fromDate daysTo toDate
      case x =>
        throw new Exception(s"Unkown time unit: $x")
    }
  }
}