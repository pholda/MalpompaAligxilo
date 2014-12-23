package pl.pej.malpompaligxilo.form.field

import org.scalajs.jquery.{JQuery, jQuery}
import pl.pej.malpompaligxilo.form.{Expression, Field, Form}
import pl.pej.malpompaligxilo.util._

case class CalculateField[T /*<: HTMLable*/](
  name: String,
  caption: I18nableString,
  formula: Expression[T]
                                              ) extends Field[T] {
//  override def validate: (T) => Option[ErrorMsg] = ???

  override def toHTML: JQuery = {
    val jq = jQuery(s"""
       |<div>${caption("eo")}: <span class="formExpression" /></div>
     """.stripMargin)

    jq.find(".formExpression").data("expression", formula)

    jq
  }

}
