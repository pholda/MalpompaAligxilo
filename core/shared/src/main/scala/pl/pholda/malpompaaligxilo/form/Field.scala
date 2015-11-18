package pl.pholda.malpompaaligxilo.form

import pl.pholda.malpompaaligxilo.form.field.{ComputeField, FieldValidationResult}
import pl.pholda.malpompaaligxilo.form.validation.FieldValidation
import pl.pholda.malpompaaligxilo.form.validation.errors.RequiredError
import pl.pholda.malpompaaligxilo.i18n.I18nableString

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

case class Field[T](
                     name: FieldName,
                     caption: I18nableString,
                     `type`: FieldType[T],
                     description: Option[I18nableString] = None,
                     placeholder: Option[I18nableString] = None,
                     visible: FormExpr[Boolean] = true,
                     required: Boolean = false,
                     customValidation: Option[FieldValidation[T]] = None,
                     store: Boolean = true,
                     changable: FormExpr[Boolean] = false
  ) extends FormElement {
  final def isComputed: Boolean = `type`.isInstanceOf[ComputeField[_]]

  def parse(values: Seq[String]): Option[T] = `type`.parse(values)

  //TODO result type should be Future
  def validate(implicit formInstance: FormInstance[_]): FieldValidationResult = {
    val errors = formInstance.fieldValue(this) match {
      case None if required => Seq(RequiredError)
      case Some(value: T) =>
        val typeErrors = `type`.validate(value).errors
        val customErrors = visible(formInstance) match {
          case true =>
            customValidation match {
              case Some(validation) =>
                Await.result(validation.validate(this, value), 10 seconds).errors
              case None =>
                Nil
            }
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
    separatedValues(value = value(formInstance))
  }

  def separatedValues(value: Option[T]): List[(String, String)] = {
    `type`.separatedValues(value).map{
      case ("", v) => s"$name" -> v
      case (k, v) => s"$name-$k" -> v
    }
  }
}
