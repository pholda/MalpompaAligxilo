package pl.pej.malpompaaligxilo.form

import org.scalajs.jquery.JQuery


abstract class FormElementFormatter {
  def apply(formElement: FormElement): JQuery
}
