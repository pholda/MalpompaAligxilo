package pl.pholda.malpompaaligxilo.dsl.expr.date

import pl.pholda.malpompaaligxilo.dsl.DslFormExpr
import pl.pholda.malpompaaligxilo.form.FormInstance
import pl.pholda.malpompaaligxilo.util.Date

case class DateCompare(operator: String, a: DslFormExpr[Any], b: DslFormExpr[Any]) extends DslFormExpr[Boolean] {
  override def apply(formInstance: FormInstance): Boolean = {
    val aDate = a(formInstance) match {
      case d: Date => d
      case x => throw new Exception(s"date was expected, found: $x")
    }
    val bDate = b(formInstance) match {
      case d: Date => d
      case x => throw new Exception(s"date was expected, found: $x")
    }

    operator match {
      case "<" => aDate < bDate
      case "<=" => aDate <= bDate
      case "=" => aDate == bDate
      case ">=" => aDate >= bDate
      case ">" => aDate > bDate
      case "!=" => aDate != bDate
    }
  }

}