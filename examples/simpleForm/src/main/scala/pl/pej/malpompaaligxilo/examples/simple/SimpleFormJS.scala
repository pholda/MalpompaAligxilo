package pl.pholda.malpompaaligxilo.examples.simple

import org.scalajs.dom
import org.scalajs.dom.Element
import pl.pholda.malpompaaligxilo.form.{JSContext, Field}
import pl.pholda.malpompaaligxilo.form.field.{TableCheckboxField, CalculateField}
import pl.pholda.malpompaaligxilo.form.field.calculateField.ProgressField
import pl.pholda.malpompaaligxilo.util.util.DatesJS

import scala.collection.mutable.ListBuffer
import scala.scalajs.js
import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport
import scala.util.Try
import org.scalajs.jquery.jQuery

object SimpleFormJS extends JSApp {
  implicit val context = JSContext

  val form = new SimpleForm({field =>
    field.`type`.arrayValue match {
      case true =>
        val checked = new ListBuffer[String]
        jQuery(s"[name='$field[]']:checked").each{(a: js.Any, e: Element) =>
          checked.append(jQuery(e).`val`().asInstanceOf[String])
          a
        }
        checked.toSeq
      case false =>
        if (jQuery(s"[name=${field.name}]").is("[type=checkbox]")) {
          jQuery(s"[name=${field.name}]").is(":checked") match {
            case true => Seq("yes")
            case false => Seq.empty
          }
        }
        Seq(jQuery(s"[name=${field.name}]").`val`().asInstanceOf[String])
    }

  })

  @JSExport
  override def main(): Unit = {
    lazy val calculableField = form.fields.collect{
      case f@Field(_, _, _, _, _, _, _, _, _, _) if f.isCalculate => f
    }

    def refresh() {
      form.fields.foreach{f: Field[_] =>
        try {
          val v = f.visible(form)
          jQuery(s"[name='${f.name}'], [data-name=${f.name}]").parents("div.form-group").css("display", v match {
            case true => "block"
            case false => "none"
          })
        } catch {
          case e: Exception =>
            println(s"erraro1 cxe ${f.name}, ")
        }
      }

      calculableField.foreach{
        case Field(name, _, pf: ProgressField, _, _, _, _, _, _, _) =>
          try {
            jQuery(s"progress[data-name='$name']").attr("max", pf.max(form)).attr("value", pf.value(form))
          } catch {
            case e: Exception => println(s"erraro3 cxe $name ${e.getMessage}")
          }
        case Field(name, _, cf: CalculateField[_], _, _, _, _, _, _, _) =>
          try {
            jQuery(s".formExpression[data-name='$name']").html(cf.evaluate(form) match {
              case Some(x) => x.toString
              case None => ""
            })
          } catch {
            case e: Exception => println(s"erraro2 cxe $name ${e.getMessage}")
          }

      }
    }

    jQuery(":input").change{e: dom.Element =>
      refresh()
    }
    refresh()

  }
}
