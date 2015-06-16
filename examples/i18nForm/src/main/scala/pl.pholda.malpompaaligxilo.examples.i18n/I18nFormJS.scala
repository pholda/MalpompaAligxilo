package pl.pholda.malpompaaligxilo.examples.i18n


import biz.enef.angulate._
import org.scalajs.dom.Element
import org.scalajs.jquery.jQuery
import pl.pholda.malpompaaligxilo.ContextJS
import pl.pholda.malpompaaligxilo.form.field.{CalculateField, CheckboxField}
import pl.pholda.malpompaaligxilo.form.{Field, PrintableCalculateFieldValue}
import pl.pholda.malpompaaligxilo.i18n.{Lang, PoCfg, PoCfgJS}

import scala.collection.mutable.ListBuffer
import scala.scalajs.js
import scala.scalajs.js.JSApp
import scala.scalajs.js.JSConverters._
import scala.scalajs.js.annotation.JSExport

object I18nFormJS extends JSApp {
  implicit val context = ContextJS

  implicit val lang: Lang = jQuery("html").attr("lang")
  implicit val poCfg: PoCfg = PoCfgJS.fromJS("formI18n")


  trait MyScope extends Scope {

    var field: js.Dictionary[Any] = js.native

    var fieldVisible: js.Function = js.native

    var calculateValue: js.Function = js.native
  }

  class FormCtrl($scope: MyScope) extends ScopeController {

    implicit val form = new I18nForm({field =>
      field.`type`.arrayValue match {
        case true =>
          val checked = new ListBuffer[String]
          jQuery(s"[name='$field[]']:checked").each{(a: js.Any, e: Element) =>
            checked.append(jQuery(e).`val`().asInstanceOf[String])
            a
          }
          checked.toSeq
        case false =>
          field.`type` match {
            case CheckboxField(default) =>
              $scope.field.get(field.name) match {
                case Some(true) =>
                  Seq("true")
                case _ =>
                  Seq.empty
              }
            case _ =>
              $scope.field.get(field.name).map(x => Seq(x.toString)).getOrElse(Seq.empty)
          }
      }

    })

    val fields = form.fields.map{f =>
      (f.name -> f).asInstanceOf[Tuple2[String, Field[_]]]
    }.toMap.toJSDictionary

    $scope.field = js.Dictionary.empty

    $scope.fieldVisible = (name: String) => try {
      fields.get(name).map(_.visible(form)).getOrElse(true)
    } catch {
      case _ => false
    }

    $scope.calculateValue = (name: String) => try {
      fields.get(name) match {
        case Some(field) =>
          field.`type` match {
            case cf: CalculateField[_] =>
              field.value(form).map{
                case printable: PrintableCalculateFieldValue =>
                  printable.str(lang, poCfg)
                case other => "other+"+other.toString
              }.getOrElse("")
            case _ =>
              ""
          }
        case _ =>
          ""
      }
    } catch {
      case _ => ""
    }
  }
  @JSExport
  override def main(): Unit = {
    val module = angular.createModule("malpompaAligxilo")

    module.controllerOf[FormCtrl]("FormCtrl")
  }
}
