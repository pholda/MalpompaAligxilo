package pl.pholda.malpompaaligxilo.form.field

import pl.pholda.malpompaaligxilo.form.FormExpr

case class CustomComputeField[T](formula: FormExpr[Option[T]]) extends ComputeField[T]