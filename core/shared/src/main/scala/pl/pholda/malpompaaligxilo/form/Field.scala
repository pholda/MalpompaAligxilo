package pl.pholda.malpompaaligxilo.form

import pl.pholda.malpompaaligxilo.form.errors.RequiredError
import pl.pholda.malpompaaligxilo.form.field.ComputeField
import pl.pholda.malpompaaligxilo.i18n.I18nableString

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
  final def isCalculate: Boolean = `type`.isInstanceOf[ComputeField[_]]

  def parse(values: Seq[String]): Option[T] = `type`.parse(values)

  def validate(implicit formInstance: FormInstance): Option[FormError] = {
    val value = formInstance.fieldValue(this)
    value match {
      case None if required => Some(RequiredError)
      case Some(v: T) =>
        `type`.validate(v).orElse {
          visible(formInstance) match {
            case true => customValidate(v)
            case _ => None
          }
        }
      case _ => None
    }
  }

  def value(implicit formInstance: FormInstance): Option[T] = {
    formInstance.fieldValue(this)
  }

  def separatedValues(implicit formInstance: FormInstance): Option[List[(String, String)]] = {
    separateValues(value)
  }
}
