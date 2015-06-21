package pl.pholda.malpompaaligxilo.angular

import biz.enef.angulate.ScopeController
import org.scalajs.jquery._
import pl.pholda.malpompaaligxilo.form.field.CalculateField
import pl.pholda.malpompaaligxilo.form.{Field, FormInstanceJS, PrintableCalculateFieldValue}
import pl.pholda.malpompaaligxilo.i18n.I18nableString

import scala.scalajs.js
import scala.scalajs.js.Dictionary
import scala.util.Try

trait FormController extends ScopeController {
  def $scope: FormScope

  def form: FormInstanceJS

  protected lazy val lang: String = Try(jQuery("html").attr("lang")).getOrElse("en")

  def fields: Dictionary[Field[_]]

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
              case string: String =>
                string
              case i18nString: I18nableString =>
                i18nString("en")
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