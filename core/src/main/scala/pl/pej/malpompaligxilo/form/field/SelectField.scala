package pl.pej.malpompaligxilo.form.field

import org.scalajs.jquery.{JQuery, jQuery}
import pl.pej.malpompaligxilo.form.Field
import pl.pej.malpompaligxilo.util._

case class SelectField(
  name: String,
  options: List[EnumOption],
  required: Boolean = false,
  size: Int = 1
                        ) extends Field {
//  override def validate: (Nothing) => Option[ErrorMsg] = ???

  override def toJQuery: JQuery = {
    val select = jQuery(s"""<select name="$name" size="$size"></select>""")
    for (option <- options) {
      select.append(option.toJQuery)
    }
    select
  }
}
