package pl.pej.malpompaligxilo.form

import org.scalajs.jquery.JQuery

abstract class FieldType[T] {
  def toJQuery(field: Field[T]): JQuery

  def validate(t: T): Option[FormError] = None

  def parse(values: Seq[String]): Option[T]
}
