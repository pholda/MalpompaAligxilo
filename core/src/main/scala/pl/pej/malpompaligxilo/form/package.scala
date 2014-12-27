package pl.pej.malpompaligxilo

/**
 * Created by piotr on 22.12.14.
 */
package object form {
  type FormExpr[T] = Form => T

  implicit def t2formExpr[T](o: T): FormExpr[T] = _ => o
}
