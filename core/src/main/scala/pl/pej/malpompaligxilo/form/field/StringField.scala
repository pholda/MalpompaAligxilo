package pl.pej.malpompaligxilo.form.field

import org.scalajs.jquery.JQuery
import pl.pej.malpompaligxilo.form.Field
import pl.pej.malpompaligxilo.util.{I18nString, ErrorMsg}

case class StringField(name: String, caption: I18nString, required: Boolean = false) extends Field[String] {
  override def validate: (String) => Option[ErrorMsg] = ???

  override def toHTML: JQuery =
    s"""<div>${caption("eo")}: <input type="text" name="$name" /></div>"""
}
