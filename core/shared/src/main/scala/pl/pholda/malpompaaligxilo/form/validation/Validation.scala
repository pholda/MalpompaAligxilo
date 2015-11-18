package pl.pholda.malpompaaligxilo.form.validation

import pl.pholda.malpompaaligxilo.form.{FormInstance, Field, ValidationResult}
import pl.pholda.malpompaaligxilo.form.db.DbReader
import pl.pholda.malpompaaligxilo.form.field.FieldValidationResult

import scala.concurrent.{ExecutionContext, Future}

sealed abstract class Validation

abstract class FieldValidation[T] extends Validation {
  def validate(field: Field[T], value: T)(implicit executionContext: ExecutionContext): Future[FieldValidationResult]
}

abstract class FormValidation extends Validation {
  def validate(dbReader: DbReader)(implicit executionContext: ExecutionContext, formInstance: FormInstance[_]): Future[ValidationResult]
}
