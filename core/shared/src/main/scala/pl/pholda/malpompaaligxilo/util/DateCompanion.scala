package pl.pholda.malpompaaligxilo.util

abstract class DateCompanion {
  def now: Date

  // ISO 8601 (YYYY-MM-DD)
  def fromString(str: String): Date
}
