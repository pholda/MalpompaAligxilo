package pl.pej.malpompaaligxilo

/**
 * Created by piotr on 22.12.14.
 */
package object form {
  type FieldName = String

  type FormExpr[T] = Form => T

  implicit def t2formExpr[T](o: T): FormExpr[T] = _ => o
}
