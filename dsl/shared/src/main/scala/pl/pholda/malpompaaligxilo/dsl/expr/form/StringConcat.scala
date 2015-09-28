package pl.pholda.malpompaaligxilo.dsl.expr.form

import pl.pholda.malpompaaligxilo.dsl.DslFormExpr
import pl.pholda.malpompaaligxilo.form.FormInstance
import pl.pholda.malpompaaligxilo.i18n.{I18nableString, NoI18nString}

case class StringConcat(a: DslFormExpr[_]*) extends DslFormExpr[Any] {
  override def apply(formInstance: FormInstance): Any = a map {expr =>
    expr(formInstance) match {
      case string: String =>
        NoI18nString(string)
      case i18nable: I18nableString =>
        i18nable
      case d: Double =>
        NoI18nString(d.formatted("%.2f"))
      case x =>
        NoI18nString(s"$x")
    }
  } reduce(_ + _)

}

















