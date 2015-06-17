package pl.pholda.malpompaaligxilo.util

abstract class Date {
  def getDay: Int
  def getMonth: Int
  def getYear: Int
  def getMillis: Long

  def ==(date: Date): Boolean = {
    getMillis == date.getMillis
  }

  def >(d: Date): Boolean = {
    getMillis > d.getMillis
  }

  def >=(d: Date): Boolean = {
    (this > d) || (this equals d)
  }

  def <(d: Date): Boolean = {
    ! >=(d)
  }

  def <=(d: Date): Boolean = {
    ! >(d)
  }
}
