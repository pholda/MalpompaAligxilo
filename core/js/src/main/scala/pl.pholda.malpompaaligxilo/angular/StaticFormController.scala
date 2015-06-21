package pl.pholda.malpompaaligxilo.angular

import pl.pholda.malpompaaligxilo.ContextJS
import pl.pholda.malpompaaligxilo.form.{Field, FormInstanceJS, FormSpecification}

import scala.scalajs.js.Dictionary
import scala.scalajs.js.JSConverters._

/**
 * this controller should be used when a form is static (not loaded dynamically by AJAX and parsed by DSL)
 * @param $scope
 * @param context
 */
abstract class StaticFormController(
    $scope: FormScope
  )(
    implicit context: ContextJS
  ) extends FormController {

  val formSpecification: FormSpecification

  lazy val form: FormInstanceJS = new FormInstanceJS(formSpecification, $scope)

  lazy val fields: Dictionary[Field[_]] = form.fields.map{f =>
    (f.name -> f).asInstanceOf[Tuple2[String, Field[_]]]
  }.toMap.toJSDictionary


}
