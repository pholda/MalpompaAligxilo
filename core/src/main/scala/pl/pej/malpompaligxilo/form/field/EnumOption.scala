package pl.pej.malpompaligxilo.form.field

import org.scalajs.jquery.JQuery
import pl.pej.malpompaligxilo.util._

case class EnumOption(
  value: String,
  caption: I18nableString
                       ) extends HTMLable {
  override def toJQuery: JQuery = s"""<option value="$value">${caption("eo")}</option>"""
}
