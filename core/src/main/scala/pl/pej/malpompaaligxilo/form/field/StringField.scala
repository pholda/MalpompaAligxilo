package pl.pej.malpompaaligxilo.form.field

import org.scalajs.jquery.JQuery
import pl.pej.malpompaaligxilo.form._
import pl.pej.malpompaaligxilo.util._

case class StringField(textarea: Boolean = false) extends FieldType[String] {
  import ToJQueryable.string2jQuery

  override def toJQuery(field: Field[String]): JQuery = {
    if (textarea) {
      s"""<textarea name="${field.name}" /*placeholder="${field.placeholder.map(_("eo")).getOrElse("")}" */></textarea>"""
    } else {
      s"""<input type="text" name="${field.name}" placeholder="${field.placeholder.map(_("eo")).getOrElse("")}" />"""
    }
  }

  override def parse(values: Seq[String]): Option[String] = values.headOption
}