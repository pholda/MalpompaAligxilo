package pl.pholda.malpompaaligxilo.util

import org.joda.time.{Days, Months, Years, DateTime}

class DateJVM(protected val jodaDate: DateTime) extends Date  {
  override def getDay: Int = jodaDate.getDayOfMonth

  override def getMonth: Int = jodaDate.getMonthOfYear

  override def getYear: Int = jodaDate.getYear

  override def getMillis: Long = jodaDate.getMillis

  override def getDayOfWeek: Int = jodaDate.dayOfWeek().get()

  override def toString: String = iso8601

  override def iso8601: String = jodaDate.toString("yyyy-MM-dd")

  override def yearsTo(to: Date): Int = {
    to match {
      case toJVM: DateJVM =>
        Years.yearsBetween(this.jodaDate, toJVM.jodaDate).getYears
      case _ =>
        throw new IllegalArgumentException("DateJVM was expected")
    }
  }

  override def monthsTo(to: Date): Int = {
    to match {
      case toJVM: DateJVM =>
        Months.monthsBetween(this.jodaDate, toJVM.jodaDate).getMonths
      case _ =>
        throw new IllegalArgumentException("DateJVM was expected")
    }
  }

  override def daysTo(to: Date): Int = {
    to match {
      case toJVM: DateJVM =>
        Days.daysBetween(this.jodaDate, toJVM.jodaDate).getDays
      case _ =>
        throw new IllegalArgumentException("DateJVM was expected")
    }
  }

  override def -(days: Int): Date = ???
}

object DateJVM extends DateCompanion {

  override def now: Date = new DateJVM(DateTime.now())

  override def fromString(str: String): Date = new DateJVM(DateTime.parse(str))
}
