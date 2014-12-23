package pl.pej.malpompaligxilo.form.field

import org.scalajs.jquery.JQuery
import pl.pej.malpompaligxilo.form.Field
import pl.pej.malpompaligxilo.util._

case class EmailField(
  name: String,
  caption: I18nableString,
  placeholder: Option[I18nableString] = None
                       ) extends Field[String] {

  override def toHTML: JQuery =
  s"""<div>${caption("eo")}: <input name="$name" type="email" placeholder="${placeholder.getOrElse(caption)("eo")}" /></div>"""
}
