package pl.pholda.malpompaaligxilo.form

import pl.pholda.malpompaaligxilo.form.errors.RequiredError
import pl.pholda.malpompaaligxilo.form.field.{SuccessFieldValidation, FieldValidationResult, ComputeField}
import pl.pholda.malpompaaligxilo.i18n.I18nableString

case class Field[T](
  name: FieldName,
  caption: I18nableString,
  `type`: FieldType[T],
  description: Option[I18nableString] = None,
  placeholder: Option[I18nableString] = None,
  visible: FormExpr[Boolean] = true,
  required: Boolean = false,
  customValidate: T => FieldValidationResult = {_:T => SuccessFieldValidation},
  store: Boolean = true
  ) extends FormElement {
  final def isComputed: Boolean = `type`.isInstanceOf[ComputeField[_]]

  def parse(values: Seq[String]): Option[T] = `type`.parse(values)

  def validate(implicit formInstance: FormInstance[_]): FieldValidationResult = {
    val errors = formInstance.fieldValue(this) match {
      case None if required => Seq(RequiredError)
      case Some(value: T) =>
        val typeErrors = `type`.validate(value).errors
        val customErrors = visible(formInstance) match {
          case true => customValidate(value).errors
          case _ => Nil
        }
        typeErrors ++ customErrors
      case _ => Seq.empty
    }
    FieldValidationResult(errors:_*)
  }

  def value(implicit formInstance: FormInstance[_]): Option[T] = {
    formInstance.fieldValue(this)
  }

  def separatedValues(implicit formInstance: FormInstance[_]): List[(String, String)] = {
    `type`.separatedValues(value).map{
      case ("", v) => s"$name" -> v
      case (k, v) => s"$name-$k" -> v
    }
  }
}
