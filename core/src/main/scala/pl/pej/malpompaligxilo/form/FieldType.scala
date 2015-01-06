package pl.pej.malpompaligxilo.form

import org.scalajs.jquery.JQuery

abstract class FieldType[T] {
  @deprecated("use own templates, for javascript use getJQuery method")
  def toJQuery(field: Field[T]): JQuery

  def getJQuery(field: Field[T]): Option[JQuery] = None

  def validate(t: T): Option[FormError] = None

  def parse(values: Seq[String]): Option[T]
}
