package pl.pej.util

import org.joda.time.DateTime

sealed abstract class Dates {
  def getNowMillis: Long

  def str2millis(str: String): Long
}

object DatesJS extends Dates {
  override def getNowMillis: Long = scalajs.js.Date.now().toLong

  override def str2millis(str: String): Long = new scalajs.js.Date(str).getTime().toLong
}

object DatesScala extends Dates {
  override def getNowMillis: Long = DateTime.now().getMillis

  override def str2millis(str: String): Long = DateTime.parse(str).getMillis
}