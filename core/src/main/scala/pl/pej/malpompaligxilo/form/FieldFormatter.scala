package pl.pej.malpompaligxilo.form

import org.scalajs.jquery.JQuery


abstract class FieldFormatter {
  def apply(field: Field[_]): JQuery
}
