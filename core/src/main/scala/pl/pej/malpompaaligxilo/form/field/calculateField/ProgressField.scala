package pl.pej.malpompaaligxilo.form.field.calculateField

import pl.pej.malpompaaligxilo.form.FormExpr
import pl.pej.malpompaaligxilo.form.field.CalculateField

abstract class ProgressField extends CalculateField[Int] {
  def max: FormExpr[Int]

  def value: FormExpr[Int]

  final override protected def formula: FormExpr[Option[Int]] = {form =>
      Some(max(form))
  }
}
