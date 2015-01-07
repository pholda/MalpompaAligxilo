package pl.pej.malpompaligxilo.form.field

import org.scalajs.jquery.{JQuery, jQuery}
import pl.pej.malpompaligxilo.form.{Form, FieldType, FormExpr, Field}
import pl.pej.malpompaligxilo.util._

case class CalculateField[T](
  formula: FormExpr[T]
                              ) extends FieldType[T] {

  override def toJQuery(field: Field[T]): JQuery = {
    val jq = jQuery(s"""
       |<span class="formExpression" ></span>
     """.stripMargin)

    jq.data("expression", formula)

    jq
  }

  override def parse(values: Seq[String]): Option[T] = None
}
