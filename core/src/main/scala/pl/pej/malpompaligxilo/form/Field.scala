package pl.pej.malpompaligxilo.form

import org.scalajs.jquery.JQuery
import pl.pej.malpompaligxilo.form.errors.RequiredError
import pl.pej.malpompaligxilo.util._

case class Field[T](
  name: FieldName,
  caption: I18nableString,
  `type`: FieldType[T],
  description: Option[I18nableString] = None,
  placeholder: Option[I18nableString] = None,
  visible: FormExpr[Boolean] = _ => true,
  required: Boolean = false,
  customValidate: T => Option[FormError] = {_:T => None}
                     ) extends FormElement with ToJQueryable {
  override def toJQuery: JQuery = `type`toJQuery(this)

  def parse(values: Seq[String]): Option[T] = `type`.parse(values)

  def validate(value: Option[Any]): Option[FormError] = {
    value match {
      case None if required => Some(RequiredError)
      case Some(v:T) =>
        customValidate(v)
      case _ => None
    }
  }
}