package pl.pej.malpompaaligxilo.form.field

import org.scalajs.jquery.{JQuery, jQuery}
import pl.pej.malpompaaligxilo.form.errors.NothingSelectedError
import pl.pej.malpompaaligxilo.form.{FormError, FieldType, FormExpr, Field}
import pl.pej.malpompaaligxilo.util._

case class SelectField(
  options: List[EnumOption],
  size: Int = 1,
  notSelected: Option[EnumOption] = None
                        ) extends FieldType[EnumOption] {

  lazy val allOptions = notSelected match {
    case Some(o) => o :: options
    case None => options
  }

  override def toJQuery(field: Field[EnumOption]): JQuery = {
    val select = jQuery(s"""<select name="${field.name}" size="${size}"></select>""")
    for (option <- allOptions) {
      select.append(option.toJQuery)
    }
    select
  }

  override def parse(values: Seq[String]): Option[EnumOption] =
    values.headOption.flatMap(v => allOptions.find(_.value == v)).orElse(notSelected)

  override def validate(t: EnumOption): Option[FormError] = {
    notSelected match {
      case Some(ns) if ns.value == t.value => Some(NothingSelectedError)
      case _ => None
    }
  }
}
