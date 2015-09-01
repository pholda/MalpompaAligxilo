package pl.pholda.malpompaaligxilo.form

abstract class FormExpr[+T] extends (FormInstance => T){
  def apply(formInstance: FormInstance): T
}

object FormExpr {
  def apply[T](implicit expr: FormInstance => T) = new FormExpr[T] {
    override def apply(formInstance: FormInstance): T = expr(formInstance)
  }

//  implicit def fun2formExpr[T](expr: FormInstance => T) {}
}
