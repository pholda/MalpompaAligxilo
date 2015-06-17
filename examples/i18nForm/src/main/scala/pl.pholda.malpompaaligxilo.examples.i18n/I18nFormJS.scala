package pl.pholda.malpompaaligxilo.examples.i18n


import biz.enef.angulate._
import org.scalajs.jquery.jQuery
import pl.pholda.malpompaaligxilo.ContextJS
import pl.pholda.malpompaaligxilo.angular.{FormController, FormScope}
import pl.pholda.malpompaaligxilo.i18n.{I18nJS, Lang}

import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport

object I18nFormJS extends JSApp {
  implicit val context = new ContextJS(new I18nJS("i18nForm"))

  implicit val lang: Lang = jQuery("html").attr("lang")

  class I18nFormCtrl($scope: FormScope) extends FormController($scope, new I18nForm())

  @JSExport
  override def main(): Unit = {
    val module = angular.createModule("malpompaAligxilo")

    module.controllerOf[I18nFormCtrl]("FormCtrl")
  }
}
