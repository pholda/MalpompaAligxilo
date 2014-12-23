package pl.pej.malpompaligxilo.form.field

import org.scalajs.jquery.JQuery
import pl.pej.malpompaligxilo.form.Field
import pl.pej.malpompaligxilo.util._

case class StringField(
  name: String,
  caption: I18nableString,
  description: Option[I18nableString] = None,
  placeholder: Option[I18nableString] = None,
  required: Boolean = false,
  textarea: Boolean = false
                        ) extends Field[String] {
//  override def validate: (String) => Option[ErrorMsg] = ???

  override def toHTML: JQuery = {
    if (textarea) {
      s"""<div>${caption("eo")}: <textarea name="$name" placeholder="${placeholder.getOrElse(caption)("eo")}" ></textarea></div>"""
    } else {
      s"""<div>${caption("eo")}: <input type="text" name="$name" placeholder="${placeholder.getOrElse(caption)("eo")}" /></div>"""
    }
  }
}
