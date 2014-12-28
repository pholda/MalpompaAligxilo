package pl.pej.malpompaligxilo.form

import org.scalajs.jquery.JQuery

abstract class FieldType[T] {
  def toJQuery(field: Field[T]): JQuery

  def parse(values: Seq[String]): Option[T]
}
