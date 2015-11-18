package pl.pholda.malpompaaligxilo.examples.dsl

//import biz.enef.angulate.{Directive, angular}
//import biz.enef.angulate.core.{Attributes, JQLite, HttpService, QService}
import com.greencatsoft.angularjs._
import com.greencatsoft.angularjs.core.{Location, Scope}
import com.greencatsoft.angularjs.internal.Configuration
import org.scalajs.dom.Element

import scala.scalajs.js
import scala.scalajs.js.{Any, JSApp}
import scala.scalajs.js.annotation.JSExport

object Form extends JSApp {
  @JSExport
  override def main(): Unit = {
//    val module = angular.createModule("malpompaAligxilo", "ui.date" :: Nil)


//    module.controllerOf[FormCtrl]("FormCtrl")

//    module.directiveOf[TestDir]

    val module = Angular.module("malpompaAligxilo", "ui.date" :: Nil)
    module.controller[FormCtrl]
    module.directive[TestDir]
  }
}

class FormScope extends Scope {

}

@js.native
trait ModelScope extends js.Object{
  var $validators: js.Dictionary[js.Any] = js.native
}


@JSExport
@injectable("blabla")
class TestDir extends AttributeDirective {

  println(s" test dir created")


  override def buildConfig(config: Configuration): Configuration = {
    config.update("require", "ngModel")
    super.buildConfig(config)
  }

//  override type ScopeType = ModelScope



  override def link(scope: ScopeType, elems: Seq[Element], attrs: Attributes): Unit = {
    controller match {
      case Some(ctrl) =>
        println(s"controller = ${ctrl}")
      case _ =>
        println("empty ctrl!")
    }
    println(elems)

//    println(s"$$vaidators = ${scope.$validators}")
//    println(s"link #1")
  }

  override def link(scope: ScopeType, elems: Seq[Element], attrs: Attributes, controller: Either[Controller[_], js.Any]*): Unit = {
    println(s"link #2")
    println(s"controllers count ${controller.size}")
    controller.head match {
      case Right(ctrl) =>
        val c = ctrl.asInstanceOf[ModelScope]
        println("validators = "+c.$validators)
        val f = new js.Function2[js.Any, js.Any, Boolean] {
          override def apply(arg1: Any, arg2: Any): Boolean = {
            false
          }
        }
        c.$validators("dupa") = f
        println(s"ctrl = $ctrl")
      case ctrl =>
        println(s"no ctrl $ctrl")
    }
  }
}
//@injectable("testDir")
//class TestDir extends AttributeDirective {
//  println(s"Aaaaa")
//  override def link(scope: ScopeType, elems: Seq[Element], attrs: Attributes): Unit = {
//    println(s">> kontroler ${controller.get}")
//  }
//}

@injectable("FormCtrl")
class FormCtrl(scope: FormScope) extends AbstractController[FormScope](scope) {

}


//case class FormCtrl(
//  $scope: FormScope,
//  $http: HttpService,
//  $sce: StrictContextualEscaping,
//  $q: QService
//) extends DslFormController {
//  $scope.dateOptions = js.Dictionary(
//    "dateFormat" -> "yy-mm-dd", // ISO
//    "changeYear" -> true,
//    "changeMonth" -> true
//  )
//
//  override def specificationUrl: String = "specification"
//  override def poUrl: String = s"po/$lang"
//
//  $scope.submit = () => {
//    val data = js.Dictionary(fields.filter{
//      case (_, field) => !field.isComputed
//    }.mapValues(field => js.Array(form.getRawFieldValue(field):_*)).toSeq:_*)
//    $http.post("/", data).success((data: js.Any) => {
//      dom.alert("success!!")
//    })
//  }
//}