package pl.pej.malpompaaligxilo.form.field

import org.scalajs.jquery.JQuery
import pl.pej.malpompaaligxilo.form._
import pl.pej.malpompaaligxilo.util._

case object EmailField extends FieldType[String] {
  import ToJQueryable.string2jQuery

  override def toJQuery(field: Field[String]): JQuery =
  s"""<input name="${field.name}" type="email" placeholder="${field.placeholder.map(_("eo")).getOrElse("")}" />"""

  override def parse(values: Seq[String]): Option[String] = values.headOption
}
