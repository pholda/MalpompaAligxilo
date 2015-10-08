package pl.pholda.malpompaaligxilo.form

abstract class FormExpr[+T] extends (FormInstance[_] => T){
  def apply(formInstance: FormInstance[_]): T
}

object FormExpr {
  def apply[T](implicit expr: FormInstance[_] => T) = new FormExpr[T] {
    override def apply(formInstance: FormInstance[_]): T = expr(formInstance)
  }
}
