package pl.pej.malpompaaligxilo.util

import org.joda.time.DateTime

object DatesScala extends Dates {

  override def now: Date = new ScalaDate(DateTime.now())

  override def fromString(str: String): Date = new ScalaDate(DateTime.parse(str))

  override def getNowMillis: Long = DateTime.now().getMillis

  override def str2millis(str: String): Long = DateTime.parse(str).getMillis
}
