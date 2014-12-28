package pl.pej.malpompaligxilo.form


sealed abstract class ValidationResult

case object SuccessValidation extends ValidationResult

case class FailureValidation(errors: Map[Field[_], FormError]) extends ValidationResult
