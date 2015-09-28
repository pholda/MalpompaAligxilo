package pl.pholda.malpompaaligxilo.form.field

import pl.pholda.malpompaaligxilo.form.FormError

sealed abstract class FieldValidationResult {
  def errors: Seq[FormError]

  def isSuccess: Boolean
}

case object SuccessFieldValidation extends FieldValidationResult {
  override val errors: Seq[FormError] = Seq.empty

  override def isSuccess: Boolean = true
}

case class FailureFieldValidation(errors: FormError*) extends FieldValidationResult {
  override def isSuccess: Boolean = false
}

object FieldValidationResult {
  def apply(errors: FormError*): FieldValidationResult = {
    if (errors.isEmpty)
      SuccessFieldValidation
    else
      FailureFieldValidation(errors:_*)
  }
}