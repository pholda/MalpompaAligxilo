package pl.pej.malpompaligxilo.generator

import org.scalajs.dom
import org.scalajs.dom.Element
import org.scalajs.jquery.{JQueryEventObject, jQuery}
import pl.pej.malpompaligxilo.form.field._
import pl.pej.malpompaligxilo.form.{Expression, Form}
import pl.pej.malpompaligxilo.util.I18nString

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport
import scala.scalajs.js.{JSApp, ThisFunction}

object Generator extends JSApp {



  @JSExport
  def main(): Unit = {
    val form = Form(
      "aligxilo",
      "AligxiloTest.php",
      StringField(
        name = "nomo",
        caption = I18nString(
          "eo" -> "Nomo",
          "pl" -> "Imie"
        )
      ),
      SelectField(
        name = "genro",
        caption = I18nString(
          "eo" -> "Genro",
          "pl" -> "Płeć"
        ),
        options = List(
          EnumOption(
            value = "ina",
            caption = I18nString(
              "eo" -> "Ina",
              "pl" -> "Żeńska"
            )
          ),
          EnumOption(
            value = "malina",
            caption = I18nString(
              "eo" -> "Malina",
              "pl" -> "Męska"
            )
          ),
          EnumOption(
            value = "alia",
            caption = I18nString(
              "eo" -> "Alia",
              "pl" -> "Inna"
            )
          )
        )
      ),
      CalculateField[String](
        name = "2xnomo",
        caption = I18nString(
          "eo" -> "2·nomo"
        ),
        formula = new Expression[String]{

          override def apply(form: Form): String = {
            form.getFieldValue("nomo").toString * 2
          }
        }
      )

    )

    val jqForm = form.toHTML

    jQuery("body").append(jqForm)

    def refresh() {
      jqForm.find(".formExpression").each(new Function2[js.Any, dom.Element, js.Any]{
        override def apply(v1: js.Any, element: Element): js.Any = {
          jQuery(element).html(jQuery(element).data("expression").asInstanceOf[js.Function1[Form, String]].apply(form))
        }})
    }

    jqForm.find(":input").change{e: dom.Element =>
      refresh()
    }
  }
}