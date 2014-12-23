package pl.pej.malpompaligxilo.form.field

import org.scalajs.jquery.JQuery
import pl.pej.malpompaligxilo.form.Field
import pl.pej.malpompaligxilo.util._

case class EmailField(
  name: String,
  placeholder: Option[I18nableString] = None
                       ) extends Field[String] {

  override def toJQuery: JQuery =
  s"""<input name="$name" type="email" placeholder="${placeholder.map(_("eo")).getOrElse("")}" />"""
}
