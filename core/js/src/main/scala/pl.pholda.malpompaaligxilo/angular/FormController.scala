package pl.pholda.malpompaaligxilo.angular

import biz.enef.angulate.ScopeController
import org.scalajs.jquery._
import pl.pholda.malpompaaligxilo.form.field.ComputeField
import pl.pholda.malpompaaligxilo.form.field.calculateField.cost.{CostsField, CostValue}
import pl.pholda.malpompaaligxilo.form.{Field, FormInstanceJS, PrintableComputeFieldValue}
import pl.pholda.malpompaaligxilo.i18n.I18nableString

import scala.scalajs.js
import scala.scalajs.js.Dictionary
import scala.util.Try

trait FormController extends ScopeController {
  def $scope: FormScope

  def form: FormInstanceJS[_]

  protected lazy val lang: String = Try(jQuery("html").attr("lang")).getOrElse("en")

  def fields: Dictionary[Field[_]]

  $scope.fieldValue = js.Dictionary.empty

  $scope.fieldVisible = (name: String) => try {
    fields.get(name).map(_.visible(form)).getOrElse(true)
  } catch {
    case _ => false
  }

  $scope.submitted = false

  $scope.computedValue = (name: String) => try {
    fields.get(name) match {
      case Some(field) =>
        field.`type` match {
          case costsField: CostsField =>
            costsField.printer(field.value(form).map(_.asInstanceOf[CostValue]).get)(lang)
          case cf: ComputeField[_] =>
            field.value(form).map{
              case printable: PrintableComputeFieldValue =>
                printable.str(lang, form.context.translationProvider)
              case string: String =>
                string
              case i18nString: I18nableString =>
                i18nString(lang)
              case x =>
                x.toString
            }.getOrElse("")
          case _ =>
            ""
        }
      case _ =>
        ""
    }
  } catch {
    case e: Exception => ""
  }
}