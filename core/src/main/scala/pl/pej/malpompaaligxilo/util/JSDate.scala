package pl.pej.malpompaaligxilo.util

import scala.scalajs.js

class JSDate(jsDate: js.Date) extends Date {
  override def getDay: Int = jsDate.getDay()

  override def getMonth: Int = jsDate.getMonth()

  override def getYear: Int = jsDate.getFullYear()

  override def getMillis: Long = jsDate.getMilliseconds()

  override def toString: Lang = s"$getYear-$getMonth-$getDay"
}
