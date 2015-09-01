package pl.pholda.malpompaaligxilo.examples.i18n

import biz.enef.angulate._
import org.scalajs.jquery.jQuery
import pl.pholda.malpompaaligxilo.ContextJS
import pl.pholda.malpompaaligxilo.angular.{FormScope, StaticFormController}
import pl.pholda.malpompaaligxilo.form.FormSpecification
import pl.pholda.malpompaaligxilo.i18n.{JSObjectTranslationProvider, Lang}

import scala.scalajs.js
import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport

object I18nFormJS extends JSApp {
  implicit val context = new ContextJS()(new JSObjectTranslationProvider(js.Dynamic.global.window.i18n))

  implicit val lang: Lang = jQuery("html").attr("lang")

  class I18nFormCtrl(val $scope: FormScope) extends StaticFormController($scope) {
    override val formSpecification: FormSpecification = new I18nFormSpec()
  }

  @JSExport
  override def main(): Unit = {
    val module = angular.createModule("malpompaAligxilo", "ui.date" :: Nil)

    module.controllerOf[I18nFormCtrl]("FormCtrl")
  }
}
