package pl.pholda.malpompaaligxilo.form.field

import pl.pholda.malpompaaligxilo.Context
import pl.pholda.malpompaaligxilo.form._
import pl.pholda.malpompaaligxilo.util.Date

import scala.util.Try

case class DateField(
                      minDate: Option[String] = None,
                      maxDate: Option[String] = None,
                      yearRange: Option[String] = None)
                  (implicit context: Context) extends FieldType[Date]{
  override def parse(values: Seq[String]): Option[Date] =
  Try(values.headOption.map(str => context.date.fromString(str))).toOption.flatten

  override val arrayValue: Boolean = false
}
