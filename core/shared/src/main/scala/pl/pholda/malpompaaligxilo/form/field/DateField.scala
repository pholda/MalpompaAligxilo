package pl.pholda.malpompaaligxilo.form.field

import pl.pholda.malpompaaligxilo.Context
import pl.pholda.malpompaaligxilo.form._
import pl.pholda.malpompaaligxilo.util.Date

import scala.util.Try

case class DateField(
                      minDate: Option[Date] = None,
                      maxDate: Option[Date] = None,
                      yearRange: Option[String] = None
                    )
                  (implicit context: Context) extends FieldType[Date]{
  override def parse(values: Seq[String]): Option[Date] =
  Try(values.headOption.map(str => context.date.fromString(str))).toOption.flatten

  override val arrayValue: Boolean = false

  override def separatedValues(value: Option[Date]): List[(String, String)] = value match {
    case Some(date) => "" -> date.toString :: Nil
    case _ => Nil
  }
}
