package pl.pej.malpompaaligxilo.util

import scala.scalajs.js

object DatesJS extends Dates {
  override def now: Date = new JSDate(new js.Date())

  override def fromString(str: String): Date = new JSDate(new js.Date(str))

  override def getNowMillis: Long = scalajs.js.Date.now().toLong

  override def str2millis(str: String): Long = new scalajs.js.Date(str).getTime().toLong
}