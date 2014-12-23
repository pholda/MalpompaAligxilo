package pl.pej.malpompaligxilo.form.field

import org.scalajs.jquery.{JQuery, jQuery}
import pl.pej.malpompaligxilo.form.Field
import pl.pej.malpompaligxilo.util.I18nString

case class DateField(name: String, caption: I18nString, placeholder: Option[I18nString] = None) extends Field[String]{
  override def toHTML: JQuery = {
    s"""<div>${caption("eo")}: <input type="text" class="formDate" name="$name" placeholder="${placeholder.getOrElse(caption)("eo")}"/></div>"""
  }
}
