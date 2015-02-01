package pl.pej.malpompaaligxilo.util

abstract class Dates {
  def getNowMillis: Long

  def str2millis(str: String): Long
}

