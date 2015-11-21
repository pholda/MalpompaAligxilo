package pl.pholda.malpompaaligxilo.form.field

import pl.pholda.malpompaaligxilo.Context
import pl.pholda.malpompaaligxilo.form.FieldType
import pl.pholda.malpompaaligxilo.i18n.I18nableString
import pl.pholda.malpompaaligxilo.util.{DateRange, Date}

case class DateRangeField(
  minDate: Option[Date],
  maxDate: Option[Date],
  fromPlaceholder: Option[I18nableString] = None,
  toPlaceholder: Option[I18nableString] = None
                         )(implicit context: Context) extends FieldType[DateRange] {
  override def parse(values: Seq[String]): Option[DateRange] = ???

  override def arrayValue: Boolean = ???

  override def separatedValues(value: Option[DateRange]): List[(String, String)] = ???
}
