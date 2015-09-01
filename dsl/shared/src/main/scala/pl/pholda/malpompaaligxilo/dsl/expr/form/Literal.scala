package pl.pholda.malpompaaligxilo.dsl.expr.form

import pl.pholda.malpompaaligxilo.dsl.DslFormExpr
import pl.pholda.malpompaaligxilo.form.FormInstance

/**
 * Created by piotr on 22.06.15.
 */
case class Literal[T](value: T) extends DslFormExpr[T] {
  override def apply(form: FormInstance): T = value
}
