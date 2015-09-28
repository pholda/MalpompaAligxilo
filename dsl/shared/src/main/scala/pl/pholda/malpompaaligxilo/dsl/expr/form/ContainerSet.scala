package pl.pholda.malpompaaligxilo.dsl.expr.form

import pl.pholda.malpompaaligxilo.dsl.DslFormExpr
import pl.pholda.malpompaaligxilo.form.FormInstance

case class ContainerSet(elements: List[DslFormExpr[_]]) extends DslFormExpr[Set[_]] {
  override def apply(formInstance: FormInstance): Set[_] = {
    elements.map(_(formInstance)).toSet
  }
}
