package pl.pholda.malpompaaligxilo.util


import pl.pholda.malpompaaligxilo.i18n.Lang

import scala.scalajs.js

class JSDate(jsDate: js.Date) extends Date {
  override def getDay: Int = jsDate.getDate()

  override def getMonth: Int = jsDate.getMonth()+1

  override def getYear: Int = jsDate.getFullYear()

  override def getMillis: Long = jsDate.getTime().toLong

  override def toString: String = "%04d".format(getYear) + "-" + "%02d".format(getMonth) + "-" + "%02d".format(getDay)
}

object JSDate extends DateCompanion {
  override def now: Date = new JSDate(new js.Date())

  override def fromString(str: String): Date = new JSDate(new js.Date(str))
}