package pl.pej.malpompaaligxilo.form.field

import pl.pej.malpompaaligxilo.form._
import pl.pej.malpompaaligxilo.util.{Dates, Date}

import scala.util.Try

case class DateField(
                      minDate: Option[String] = None,
                      maxDate: Option[String] = None,
                      yearRange: Option[String] = None)
                  (implicit dates: Dates) extends FieldType[Date]{
//  override def parse(values: Seq[String]): Option[String] = values.headOption
  override def parse(values: Seq[String]): Option[Date] =
  Try(values.headOption.map(str => dates.fromString(str))).toOption.flatten
}
