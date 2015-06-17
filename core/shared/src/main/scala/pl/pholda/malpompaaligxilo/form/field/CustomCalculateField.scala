package pl.pholda.malpompaaligxilo.form.field

import pl.pholda.malpompaaligxilo.form.FormExpr

case class CustomCalculateField[T](formula: FormExpr[Option[T]]) extends CalculateField[T]
