package pl.pholda.malpompaaligxilo.util

abstract class DateCompanion {
  def now: Date

  def fromString(str: String): Date
}
