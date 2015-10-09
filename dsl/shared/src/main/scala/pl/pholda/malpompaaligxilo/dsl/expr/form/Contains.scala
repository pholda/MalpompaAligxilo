package pl.pholda.malpompaaligxilo.dsl.expr.form

import pl.pholda.malpompaaligxilo.dsl.DslFormExpr
import pl.pholda.malpompaaligxilo.form.FormInstance

case class Contains(container: DslFormExpr[_], element: DslFormExpr[_]) extends DslFormExpr[Boolean] {
  override def apply(formInstance: FormInstance[_]): Boolean = {
    container(formInstance) match {
      case i: {def contains(e: Any): Boolean} =>
        i contains element(formInstance)
      case _ =>
        false
    }
  }
}
