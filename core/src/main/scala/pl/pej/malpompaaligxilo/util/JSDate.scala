package pl.pej.malpompaaligxilo.util

import scala.scalajs.js

class JSDate(jsDate: js.Date) extends Date {
  override def getDay: Int = jsDate.getDate()

  override def getMonth: Int = jsDate.getMonth()+1

  override def getYear: Int = jsDate.getFullYear()

  override def getMillis: Long = jsDate.getTime().toLong

  override def toString: Lang = "%04d".format(getYear) + "-" + "%02d".format(getMonth) + "-" + "%02d".format(getDay)
}
