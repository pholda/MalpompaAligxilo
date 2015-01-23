package pl.pej.malpompaaligxilo.jes2015

import org.scalajs.dom
import org.scalajs.dom.Element
import org.scalajs.jquery.jQuery
import pl.pej.malpompaaligxilo.form.Field
import pl.pej.malpompaaligxilo.form.field.CalculateField
import pl.pej.util.DatesJS

import scala.collection.mutable.ListBuffer
import scala.scalajs.js
import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport
import scala.util.Try

object Jes2015Form extends JSApp {
  private val form = new Jes2015Aligxilo(DatesJS, {field =>
    Try{
      if (jQuery(s"[name=$field]").is("[type=checkbox]")) {
        jQuery(s"[name=$field]").is(":checked") match {
          case true => Seq("jes")
          case false => Seq.empty
        }
      } else {
        Seq(jQuery(s"[name=$field]").`val`().asInstanceOf[String])
      }
    }.getOrElse{
      Try{
        val checked = new ListBuffer[String]
        jQuery(s"[name='$field[]']:checked").each{(a: js.Any, e: Element) =>
          checked.append(jQuery(e).`val`().asInstanceOf[String])
          a
        }
        checked.toSeq
      }.getOrElse(throw new Exception(s"cannot read field $field"))
    }

  })

  @JSExport
  def main(): Unit = {

    lazy val calculableField = form.fields.collect{
      case f@Field(_, _, CalculateField(_), _, _, _, _, _, _) => f
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
        case Field(name, _, CalculateField(formula), _, _, _, _, _, _) =>
          try {
            jQuery(s".formExpression[data-name='$name']").html(formula(form) match {
              case d: js.prim.Number => d.toFixed(2)
              case x => x.toString
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