package pl.pej.malpompaaligxilo.form.field

import pl.pej.malpompaaligxilo.form._

case class DateField(minDate: Option[String] = None, maxDate: Option[String] = None, yearRange: Option[String] = None) extends FieldType[String]{
  override def parse(values: Seq[String]): Option[String] = values.headOption
}
