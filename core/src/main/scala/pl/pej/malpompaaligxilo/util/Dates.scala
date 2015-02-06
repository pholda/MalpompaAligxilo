package pl.pej.malpompaaligxilo.util

abstract class Dates {
  def now: Date

  def fromString(str: String): Date

  @deprecated
  def getNowMillis: Long

  @deprecated
  def str2millis(str: String): Long
}

