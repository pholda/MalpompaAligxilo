package pl.pej.malpompaligxilo.form.field

import org.scalajs.jquery.{JQuery, jQuery}
import pl.pej.malpompaligxilo.form.{FormExpression, Field, Form}
import pl.pej.malpompaligxilo.util._

case class CalculateField[T /*<: HTMLable*/](
  name: String,
  formula: FormExpression[T]
                                              ) extends Field[T] {
//  override def validate: (T) => Option[ErrorMsg] = ???

  override def toJQuery: JQuery = {
    val jq = jQuery(s"""
       |<span class="formExpression" >ohujtujh</span>
     """.stripMargin)

    jq/*.find(".formExpression")*/.data("expression", formula)
    println(formula)

    jq
  }

}
