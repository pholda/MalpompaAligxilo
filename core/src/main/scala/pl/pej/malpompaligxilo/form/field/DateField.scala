package pl.pej.malpompaligxilo.form.field

import org.scalajs.jquery.JQuery
import pl.pej.malpompaligxilo.form._

case object DateField extends FieldType[String]{
  import pl.pej.malpompaligxilo.util.ToJQueryable.string2jQuery


  override def toJQuery(field: Field[String]): JQuery = {
    s"""<input type="text" placeholder="jjjj-mm-tt" class="formDate" name="${field.name}" placeholder="${field.placeholder.map(_("eo")).getOrElse("")}"/>"""
  }

  override def parse(values: Seq[String]): Option[String] = values.headOption
}
