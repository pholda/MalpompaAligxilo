package pl.pholda.malpompaaligxilo.dsl.expr.form

import pl.pholda.malpompaaligxilo.dsl.DslFormExpr
import pl.pholda.malpompaaligxilo.form.FormInstance

/**
* Created by piotr on 22.06.15.
*/
sealed abstract class MapPattern {
  def apply(value: Any, formInstance: FormInstance[_]): Boolean
}

object MapPattern {
  case class SimplePattern(pattern: DslFormExpr[Any]) extends MapPattern {
    override def apply(value: Any, formInstance: FormInstance[_]): Boolean = {
      pattern(formInstance) == value
    }
  }
  case object DefaultPattern extends MapPattern {
    override def apply(value: Any, formInstance: FormInstance[_]): Boolean =
      true
  }
}