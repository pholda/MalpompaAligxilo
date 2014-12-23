package pl.pej.malpompaligxilo.form.field

import org.scalajs.jquery.JQuery
import org.scalajs.jquery.jQuery
import pl.pej.malpompaligxilo.form.Field
import pl.pej.malpompaligxilo.util._

case class SelectField(
  name: String,
  caption: I18nableString,
  description: Option[I18nableString] = None,
  options: List[EnumOption],
  required: Boolean = false,
  size: Int = 1
                        ) extends Field {
//  override def validate: (Nothing) => Option[ErrorMsg] = ???

  override def toHTML: JQuery = {
    val select = jQuery(s"""<select name="$name" size="$size"></select>""")
    for (option <- options) {
      select.append(option.toHTML)
    }
    jQuery(s"<div>${caption("eo")}</div>").append(select)
  }
}
