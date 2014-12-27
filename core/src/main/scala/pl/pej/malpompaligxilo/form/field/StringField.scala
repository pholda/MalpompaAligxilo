package pl.pej.malpompaligxilo.form.field

import org.scalajs.jquery.JQuery
import pl.pej.malpompaligxilo.form._
import pl.pej.malpompaligxilo.util._

case class StringField(textarea: Boolean = false) extends FieldType[String] {
  import ToJQueryable.string2jQuery

  override def toJQuery(field: Field[String]): JQuery = {
    if (textarea) {
      s"""<textarea name="${field.name}" placeholder="${field.placeholder.map(_("eo")).getOrElse("")}" ></textarea>"""
    } else {
      s"""<input type="text" name="${field.name}" placeholder="${field.placeholder.map(_("eo")).getOrElse("")}" />"""
    }
  }
}
//case class StringField(
//  name: String,
//  placeholder: Option[I18nableString] = None,
//  required: Boolean = false,
//  textarea: Boolean = false,
//  visible: FormExpr[Boolean] = true
//                        ) extends Field[String] {
////  override def validate: (String) => Option[ErrorMsg] = ???
//
//  override def toJQuery: JQuery = {
//    if (textarea) {
//      s"""<textarea name="$name" placeholder="${placeholder.map(_("eo")).getOrElse("")}" ></textarea>"""
//    } else {
//      s"""<input type="text" name="$name" placeholder="${placeholder.map(_("eo")).getOrElse("")}" />"""
//    }
//  }
//}
