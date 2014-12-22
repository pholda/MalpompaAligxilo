package pl.pej.malpompaligxilo.form.field

import org.scalajs.jquery.JQuery
import pl.pej.malpompaligxilo.util.{HTMLable, I18nString}

case class EnumOption(value: String, caption: I18nString) extends HTMLable {
  override def toHTML: JQuery = s"""<option value="$value">${caption("eo")}</option>"""
}
