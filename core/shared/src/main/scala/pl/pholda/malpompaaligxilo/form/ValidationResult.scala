package pl.pholda.malpompaaligxilo.form


sealed abstract class ValidationResult {
  def isSuccess: Boolean
}

case object SuccessValidation extends ValidationResult {
  override def isSuccess: Boolean = true
}

case class FailureValidation(errors: Map[Field[_], Seq[FormError]]) extends ValidationResult {
  override def isSuccess: Boolean = false
}
