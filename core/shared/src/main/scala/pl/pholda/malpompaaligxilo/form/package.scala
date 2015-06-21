package pl.pholda.malpompaaligxilo

package object form {
  type FieldName = String

  implicit def t2formExpr[T](o: T): FormExpr[T] = FormExpr(_ => o)
}
