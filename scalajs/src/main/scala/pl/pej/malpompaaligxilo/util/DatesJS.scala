package pl.pej.malpompaaligxilo.util

object DatesJS extends Dates {
  override def getNowMillis: Long = scalajs.js.Date.now().toLong

  override def str2millis(str: String): Long = new scalajs.js.Date(str).getTime().toLong
}