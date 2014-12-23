package pl.pej.malpompaligxilo.generator

import org.scalajs.jquery.{JQuery, jQuery}
import pl.pej.malpompaligxilo.form.{CaptionedField, Field, FieldFormatter}

object SimpleFieldFormatter extends FieldFormatter {
  override def apply(field: Field[_]): JQuery = {
    field match {
      case cf: CaptionedField[_] =>
        val div = jQuery(s"""<div />""")
        div.append(
          s"""<div class="field1">
             |<div class="fieldName">${cf.caption("eo")}:</div>
             |${cf.description match {
            case Some(desc) => s"""<div class="fieldDesc">${desc("eo")}</div>"""
            case _ => ""
          }}
             |</div>""".stripMargin)
        div.append(jQuery(s"""<div class="field2" />""").append(cf.toJQuery))
        div
      case x =>
        println(x)
        throw new UnsupportedOperationException
    }
  }
}
