package pl.pholda.malpompaaligxilo

package object form {
  type FieldName = String

  type FormExpr[T] = FormInstance => T

  implicit def t2formExpr[T](o: T): FormExpr[T] = _ => o
}
