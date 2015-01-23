package pl.pej.malpompaaligxilo.form.field

import org.scalajs.jquery.JQuery
import pl.pej.malpompaaligxilo.form._

case class DateField(minDate: Option[String] = None, maxDate: Option[String] = None, yearRange: Option[String] = None) extends FieldType[String]{
  import pl.pej.malpompaaligxilo.util.ToJQueryable.string2jQuery


  override def toJQuery(field: Field[String]): JQuery = {
    s"""<input type="text" ${
      minDate match {
        case Some(d) => s"""data-mindate="$d" """
        case _ => ""
      }
    }${
      maxDate match {
        case Some(d) => s"""data-maxdate="$d" """
        case _ => ""
      }
    }${
      yearRange match {
        case Some(d) => s"""data-yearrange="$d" """
        case _ => ""
      }
    } placeholder="jjjj-mm-tt" class="formDate" name="${field.name}" placeholder="${field.placeholder.map(_("eo")).getOrElse("")}"/>"""
  }

  override def parse(values: Seq[String]): Option[String] = values.headOption
}
