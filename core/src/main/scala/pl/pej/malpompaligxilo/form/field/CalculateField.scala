package pl.pej.malpompaligxilo.form.field

import org.scalajs.jquery.{JQuery, jQuery}
import pl.pej.malpompaligxilo.form.{Expression, Field, Form}
import pl.pej.malpompaligxilo.util.{ErrorMsg, I18nString}

case class CalculateField[T /*<: HTMLable*/](name: String, caption: I18nString, formula: Expression[T]) extends Field[T] {
  override def validate: (T) => Option[ErrorMsg] = ???

  override def toHTML: JQuery = {
    val jq = jQuery(s"""
       |<div>${caption("eo")}: <span class="formExpression" /></div>
     """.stripMargin)

    jq.find(".formExpression").data("expression", formula)

    jq
  }

}
