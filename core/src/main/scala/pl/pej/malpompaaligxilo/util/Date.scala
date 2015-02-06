package pl.pej.malpompaaligxilo.util

abstract class Date {
  def getDay: Int
  def getMonth: Int
  def getYear: Int
  def getMillis: Long

  override def equals(obj: scala.Any): Boolean = obj match {
    case d: Date => d.getMillis == getMillis
    case _ => false
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
