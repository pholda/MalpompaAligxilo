package pl.pej.malpompaaligxilo.util

import org.joda.time.DateTime

class ScalaDate(jodaDate: DateTime) extends Date  {
  override def getDay: Int = jodaDate.getDayOfMonth

  override def getMonth: Int = jodaDate.getMonthOfYear

  override def getYear: Int = jodaDate.getYear

  override def getMillis: Long = jodaDate.getMillis

  override def toString: Lang = jodaDate.toString("yyyy-MM-dd")
}
