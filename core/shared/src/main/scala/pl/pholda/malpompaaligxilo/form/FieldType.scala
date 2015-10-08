package pl.pholda.malpompaaligxilo.form

import pl.pholda.malpompaaligxilo.form.field.{FieldValidationResult, SuccessFieldValidation}

abstract class FieldType[T] {

  def validate(t: T): FieldValidationResult = SuccessFieldValidation

  def parse(values: Seq[String]): Option[T]

  def arrayValue: Boolean

  def separatedValues(value: Option[T]): List[(String, String)]
}
