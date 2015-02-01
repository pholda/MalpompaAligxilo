package pl.pej.malpompaaligxilo.util

import org.joda.time.DateTime

object DatesScala extends Dates {
  override def getNowMillis: Long = DateTime.now().getMillis

  override def str2millis(str: String): Long = DateTime.parse(str).getMillis
}
