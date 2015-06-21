package pl.pholda.malpompaaligxilo.form

abstract class FormExpr[+T] {
  def apply(formInstance: FormInstance): T
}

object FormExpr {
  def apply[T](expr: FormInstance => T) = new FormExpr[T] {
    override def apply(formInstance: FormInstance): T = expr(formInstance)
  }
}
