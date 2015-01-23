package pl.pej.malpompaaligxilo.form.field

import org.scalajs.jquery.JQuery
import pl.pej.malpompaaligxilo.form.{Field, FieldType}
import pl.pej.malpompaaligxilo.util.ToJQueryable


case class CheckboxField(default: Boolean = false) extends FieldType[Boolean] {
  import ToJQueryable.string2jQuery

  override def toJQuery(field: Field[Boolean]): JQuery =
    s"""<input type="checkbox" name="${field.name}" ${if (default) "checked " else ""} />"""

  override def parse(values: Seq[String]): Option[Boolean] = {
    Some(values.nonEmpty)
  }
}
