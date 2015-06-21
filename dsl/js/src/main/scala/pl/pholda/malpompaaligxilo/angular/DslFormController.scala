package pl.pholda.malpompaaligxilo.angular

import biz.enef.angulate.core.{HttpService, QPromise, QService}
import pl.pholda.malpompaaligxilo.ContextJS
import pl.pholda.malpompaaligxilo.dsl.parser.FormSpecificationParser
import pl.pholda.malpompaaligxilo.form.{Field, FormInstanceJS}
import pl.pholda.malpompaaligxilo.i18n._

import scala.scalajs.js
import scala.scalajs.js.Dictionary
import scala.scalajs.js.JSConverters._

trait DslFormController extends FormController {
  def $http: HttpService
  def $q: QService

  def specificationUrl: String
  def poUrl: String

  implicit var contextJS = new ContextJS(EmptyI18n)

  $q.all(js.Array(
    $http.get[String](specificationUrl).asInstanceOf[QPromise],
    $http.get[String](poUrl).asInstanceOf[QPromise]
  )).then(successCallback = {data: js.Any =>
    data match {
      case array: js.Array[js.Dynamic] =>
        implicit val i18n = new I18nJS(array(1).data)
        val parser = FormSpecificationParser()
        val formSpecification = parser(array(0).data.asInstanceOf[String]).get
        _form = new FormInstanceJS(formSpecification, $scope)
        _fields = form.fields.map{f =>
          (f.name -> f).asInstanceOf[Tuple2[String, Field[_]]]
        }.toMap.toJSDictionary
    }
    data
  }, errorCallback = {data: js.Any =>
  })

  $http.get[String]("specification").onSuccess{data =>
  }.onFailure{error =>
  }

  var _form = new FormInstanceJS(EmptyFormSpecification, $scope)

  override def form: FormInstanceJS = _form

  var _fields: Dictionary[Field[_]] = Dictionary.empty

  override def fields: Dictionary[Field[_]] = _fields
}