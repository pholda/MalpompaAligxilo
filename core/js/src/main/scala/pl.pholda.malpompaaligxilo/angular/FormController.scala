package pl.pholda.malpompaaligxilo.angular

import biz.enef.angulate.ScopeController
import pl.pholda.malpompaaligxilo.ContextJS
import pl.pholda.malpompaaligxilo.form.field.CalculateField
import pl.pholda.malpompaaligxilo.form.{PrintableCalculateFieldValue, Field, FormSpecification, FormInstanceJS}

import scala.scalajs.js
import org.scalajs.jquery.jQuery
import scala.scalajs.js.JSConverters._
import scala.util.Try

abstract class FormController(
    $scope: FormScope,
    formSpecification: FormSpecification
  )(
    implicit context: ContextJS
  ) extends ScopeController {
  val form = new FormInstanceJS(formSpecification, $scope)

  protected lazy val lang = Try(jQuery("html").attr("lang")).getOrElse("en")

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
                printable.str(lang, form.context.i18n)
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