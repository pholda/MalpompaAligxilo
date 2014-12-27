package pl.pej.malpompaligxilo.form.field

import org.scalajs.jquery.JQuery
import pl.pej.malpompaligxilo.form._
import pl.pej.malpompaligxilo.util._

case object EmailField extends FieldType[String] {
  import ToJQueryable.string2jQuery

  override def toJQuery(field: Field[String]): JQuery =
  s"""<input name="${field.name}" type="email" placeholder="${field.placeholder.map(_("eo")).getOrElse("")}" />"""
}
