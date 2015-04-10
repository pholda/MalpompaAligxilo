package pl.pej.malpompaaligxilo.form

import pl.pej.malpompaaligxilo.util.{DatesJS, DatesScala, Dates}

sealed abstract class Context {
  def dates: Dates
}

case object ScalaContext extends Context {
  override def dates: Dates = DatesScala
}

case object JSContext extends Context {
  override def dates: Dates = DatesJS
}


