package pl.pej.malpompaligxilo.generator

import org.scalajs.jquery.{JQuery, jQuery}
import pl.pej.malpompaligxilo.form.{Header, FormElement, Field, FormElementFormatter}

object SimpleFormElementFormatter extends FormElementFormatter {
  override def apply(element: FormElement): JQuery = {
    element match {
      case field: Field[_] =>
        val div = jQuery(s"""<div class="row" />""")
        div.append(
          s"""<div class="field1">
         |<div class="fieldName">${field.caption("eo")}
         |${if (field.required) """<span class="required">*</span>""" else ""}
         |${if (field.caption("eo")=="") "" else ":"}</div>
         |${field.description match {
            case Some(desc) => s"""<div class="fieldDesc">${desc("eo")}</div>"""
            case _ => ""
          }}
         |</div>""".stripMargin)
        div.append(jQuery(s"""<div class="field2" />""").append(field.toJQuery))

        div.data("visibleExpression", field.visible)
        div
      case Header(header) =>
        jQuery(s"""<div class="row"><h1>${header("eo")}</h1></div>""")
    }
  }
}
