package pl.pholda.malpompaaligxilo.util

import org.joda.time.DateTime

class DateJVM(jodaDate: DateTime) extends Date  {
  override def getDay: Int = jodaDate.getDayOfMonth

  override def getMonth: Int = jodaDate.getMonthOfYear

  override def getYear: Int = jodaDate.getYear

  override def getMillis: Long = jodaDate.getMillis

  override def toString: String = jodaDate.toString("yyyy-MM-dd")
}

object DateJVM extends DateCompanion {

  override def now: Date = new DateJVM(DateTime.now())

  override def fromString(str: String): Date = new DateJVM(DateTime.parse(str))
}
