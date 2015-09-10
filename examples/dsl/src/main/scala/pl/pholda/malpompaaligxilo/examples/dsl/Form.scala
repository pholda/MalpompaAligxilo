package pl.pholda.malpompaaligxilo.examples.dsl

import biz.enef.angulate._
import biz.enef.angulate.core.{HttpService, QService}
import pl.pholda.malpompaaligxilo.angular.{DslFormController, FormScope}

import scala.scalajs.js
import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport

object Form extends JSApp {
  @JSExport
  override def main(): Unit = {
    val module = angular.createModule("malpompaAligxilo", "ui.date" :: Nil)

    module.controllerOf[FormCtrl]("FormCtrl")
  }
}

case class FormCtrl(
  $scope: FormScope,
  $http: HttpService,
  $q: QService
) extends DslFormController {
  $scope.dateOptions = js.Dictionary(
    "dateFormat" -> "yy-mm-dd", // ISO
    "changeYear" -> true,
    "changeMonth" -> true
  )

  override def specificationUrl: String = "specification"
  override def poUrl: String = s"po/$lang"
}