package pl.pej.malpompaaligxilo.form.field

import pl.pej.malpompaaligxilo.form.FormExpr

case class CustomCalculateField[T](formula: FormExpr[Option[T]]) extends CalculateField[T]