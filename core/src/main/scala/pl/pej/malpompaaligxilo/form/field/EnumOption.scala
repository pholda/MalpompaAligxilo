package pl.pej.malpompaaligxilo.form.field

import org.scalajs.jquery.JQuery
import pl.pej.malpompaaligxilo.util._

case class EnumOption(
  value: String,
  caption: I18nableString
                       ) extends ToJQueryable {
  import ToJQueryable.string2jQuery

  override def toJQuery: JQuery = s"""<option value="$value">${caption("eo")}</option>"""
}
