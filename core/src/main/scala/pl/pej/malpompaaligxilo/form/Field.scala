package pl.pej.malpompaaligxilo.form

import pl.pej.malpompaaligxilo.form.errors.RequiredError
import pl.pej.malpompaaligxilo.form.field.CalculateField
import pl.pej.malpompaaligxilo.util._

case class Field[T](
  name: FieldName,
  caption: I18nableString,
  `type`: FieldType[T],
  description: Option[I18nableString] = None,
  placeholder: Option[I18nableString] = None,
  visible: FormExpr[Boolean] = true,
  required: Boolean = false,
  customValidate: T => Option[FormError] = {_:T => None},
  store: Boolean = true,
  separateValues: Option[T] => Option[List[(String, String)]] = {o: Option[T] => None}
                     ) extends FormElement {
  final def isCalculate: Boolean = `type`.isInstanceOf[CalculateField[_]]

  def parse(values: Seq[String]): Option[T] = `type`.parse(values)

  def validate(implicit form: Form): Option[FormError] = {
    val value = form.fieldValue(this)
    value match {
      case None if required => Some(RequiredError)
      case Some(v:T) =>
        `type`.validate(v).orElse{
          visible(form) match {
            case true => customValidate(v)
            case _ => None
          }
        }
      case _ => None
    }
  }

  def value(implicit form: Form): Option[T] = {
    form.fieldValue(this)
  }

  def separatedValues(implicit form: Form): Option[List[(String, String)]] = {
    separateValues(value)
  }
}