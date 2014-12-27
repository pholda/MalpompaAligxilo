package pl.pej.malpompaligxilo.form

import org.scalajs.jquery.JQuery
import pl.pej.malpompaligxilo.util._

case class Field[T](
  name: FieldName,
  caption: I18nableString,
  `type`: FieldType[T],
  description: Option[I18nableString] = None,
  placeholder: Option[I18nableString] = None,
  visible: FormExpr[Boolean] = _ => true,
  required: Boolean = false,
  validate: T => Boolean = {a: T => true}
                     ) extends FormElement with ToJQueryable {
  override def toJQuery: JQuery = `type`toJQuery(this)
}