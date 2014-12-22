package pl.pej.malpompaligxilo.form.field

import org.scalajs.jquery.JQuery
import org.scalajs.jquery.jQuery
import pl.pej.malpompaligxilo.form.Field
import pl.pej.malpompaligxilo.util.{ErrorMsg, I18nString}

case class SelectField(name: String, caption: I18nString, required: Boolean = false, options: List[EnumOption]) extends Field {
  override def validate: (Nothing) => Option[ErrorMsg] = ???

  override def toHTML: JQuery = {
    val select = jQuery(s"""<select name="$name"></select>""")
    for (option <- options) {
      select.append(option.toHTML)
    }
    jQuery(s"<div>${caption("eo")}</div>").append(select)
  }
}
