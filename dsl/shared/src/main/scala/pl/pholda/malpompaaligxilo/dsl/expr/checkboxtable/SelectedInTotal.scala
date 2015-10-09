package pl.pholda.malpompaaligxilo.dsl.expr.checkboxtable

import pl.pholda.malpompaaligxilo.dsl.DslFormExpr
import pl.pholda.malpompaaligxilo.form.FormInstance
import pl.pholda.malpompaaligxilo.form.field.CheckboxTableField

case class SelectedInTotal(inner: DslFormExpr[Any]) extends DslFormExpr[Int] {
  override def apply(formInstance: FormInstance[_]): Int = {
    inner.apply(formInstance) match {
      case result: CheckboxTableField.Result =>
        result.selectedInTotal
      case x =>
        throw new Exception(s"checkboxTable result was expected, found $x")
    }
  }
}
