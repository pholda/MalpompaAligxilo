package pl.pej.malpompaligxilo.form.field

import org.scalajs.jquery.{JQuery, jQuery}
import pl.pej.malpompaligxilo.form.{FieldType, FormExpr, Field}
import pl.pej.malpompaligxilo.util._

case class SelectField(
  options: List[EnumOption],
  size: Int = 1
                        ) extends FieldType[EnumOption] {

  override def toJQuery(field: Field[EnumOption]): JQuery = {
    val select = jQuery(s"""<select name="${field.name}" size="${size}"></select>""")
    for (option <- options) {
      select.append(option.toJQuery)
    }
    select
  }

  override def parse(values: Seq[String]): Option[EnumOption] =
    values.headOption.flatMap(v => options.find(_.value == v))
}
