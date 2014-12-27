package pl.pej.malpompaligxilo.form.field

import org.scalajs.jquery.{JQuery, jQuery}
import pl.pej.malpompaligxilo.form._
import pl.pej.malpompaligxilo.util.I18nString

case object DateField extends FieldType[String]{
  import pl.pej.malpompaligxilo.util.ToJQueryable.string2jQuery


  override def toJQuery(field: Field[String]): JQuery = {
    s"""<input type="text" class="formDate" name="${field.name}" placeholder="${field.placeholder.map(_("eo")).getOrElse("")}"/>"""
  }
}
