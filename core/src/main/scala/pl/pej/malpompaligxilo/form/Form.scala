package pl.pej.malpompaligxilo.form

import pl.pej.malpompaligxilo.util.ToJQueryable
import org.scalajs.jquery.{JQuery, jQuery}

/**
 *
 * @param id
// * @param action
// * @param formElementFormatter
 * @param getFieldValue - todo: to solve it in other way (macro?), that works with scalajs and without
 * @param elements
 */
case class Form/*[T]*/(
  id: String,
  /*action: String, formElementFormatter: FormElementFormatter, */
  getFieldValue: FieldName => Any,
//  parse: Map[String, Seq[String]] => T,
  elements: FormElement*)
