package pl.pholda.malpompaaligxilo.form.field.calculateField

import pl.pholda.malpompaaligxilo.form.FormExpr
import pl.pholda.malpompaaligxilo.form.field.ComputeField

abstract class ProgressField extends ComputeField[Int] {
  def max: FormExpr[Int]

  def value: FormExpr[Int]

  final override protected def formula: FormExpr[Option[Int]] = FormExpr{form =>
      Some(max(form))
  }
}
