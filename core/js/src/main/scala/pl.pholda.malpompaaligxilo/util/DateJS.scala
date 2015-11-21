package pl.pholda.malpompaaligxilo.util


import scala.scalajs.js
import org.widok.moment._

class DateJS(protected val jsDate: js.Date) extends Date {
  override def getDay: Int = jsDate.getDate()

  override def getMonth: Int = jsDate.getMonth()+1

  override def getYear: Int = jsDate.getFullYear()

  override def getMillis: Long = jsDate.getTime().toLong

  override def toString: String = iso8601

  override def iso8601: String = "%04d".format(getYear) + "-" + "%02d".format(getMonth) + "-" + "%02d".format(getDay)

  override def getDayOfWeek: Int = jsDate.getDay()

  //TODO correct calculation
  override def yearsTo(to: Date): Int = {
    to match {
      case toJS: DateJS =>
        ((jsDate.getTime() - toJS.jsDate.getTime())/1000/60/60/24/365.25).toInt
      case _ =>
        throw new IllegalArgumentException("DateJS was expected")
    }
  }

  //TODO correct calculation
  override def monthsTo(to: Date): Int = {
    to match {
      case toJS: DateJS =>
        ((jsDate.getTime() - toJS.jsDate.getTime())/1000/60/60/24/12).toInt
      case _ =>
        throw new IllegalArgumentException("DateJS was expected")
    }
  }

  override def daysTo(to: Date): Int = {
    to match {
      case toJS: DateJS =>
        ((toJS.jsDate.getTime() - jsDate.getTime())/1000/60/60/24).toInt
      case _ =>
        throw new IllegalArgumentException("DateJS was expected")
    }
  }

  override def -(days: Int): Date = {
//    Moment(jsDate).subtract(1, )
    ???
  }
}

object DateJS extends DateCompanion {
  override def now: Date = new DateJS(new js.Date())

  override def fromString(str: String): Date = new DateJS(new js.Date(str))
}
