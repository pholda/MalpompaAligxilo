package pl.pholda.malpompaaligxilo.util

import org.joda.time.DateTime

class JVMDate(jodaDate: DateTime) extends Date  {
  override def getDay: Int = jodaDate.getDayOfMonth

  override def getMonth: Int = jodaDate.getMonthOfYear

  override def getYear: Int = jodaDate.getYear

  override def getMillis: Long = jodaDate.getMillis

  override def toString: String = jodaDate.toString("yyyy-MM-dd")
}

object JVMDate extends DateCompanion {

  override def now: Date = new JVMDate(DateTime.now())

  override def fromString(str: String): Date = new JVMDate(DateTime.parse(str))
}