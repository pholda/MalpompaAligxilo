package pl.pej.malpompaligxilo.form

import org.scalajs.jquery.JQuery

abstract class FieldType[T] {
  import pl.pej.malpompaligxilo.util.ToJQueryable.string2jQuery
  def toJQuery(field: Field[T]): JQuery
}
