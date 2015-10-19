package pl.pholda.malpompaaligxilo.angular

import biz.enef.angulate.core.{HttpService, QPromise, QService}
import pl.pholda.malpompaaligxilo.ContextJS
import pl.pholda.malpompaaligxilo.dsl.form.DslFormSpecification
import pl.pholda.malpompaaligxilo.dsl.parser.FormSpecificationParser
import pl.pholda.malpompaaligxilo.form.field._
import pl.pholda.malpompaaligxilo.form.{FormSpecification, Field, FormInstanceJS}
import pl.pholda.malpompaaligxilo.i18n._

import scala.scalajs.js
import scala.scalajs.js.{JSON, Dictionary}
import scala.scalajs.js.JSConverters._
import scalajs.js.JSConverters._

trait DslFormController extends FormController {
  def $http: HttpService
  def $q: QService

  def specificationUrl: String
  def poUrl: String

  implicit var contextJS = new ContextJS()(NoTranslations)

  private def initializeFields: Unit = {
    $scope.fieldValue = fields.flatMap{case (fieldName, field) =>
      val value: Option[Any] = field.`type` match {
        case StringField(_, default) => default
        case CheckboxField(default) =>
          Option(default)
//        case IntField()
        case CheckboxTableField(rows, cols, disabled, default) =>
          Some(js.Dictionary((for {
            row <- rows
            col <- cols
            if !(disabled contains (row.id, col.id))
          } yield s"${row.id}-${col.id}" -> default):_*))
        case _ =>
          None
      }
      value.map(fieldName -> _)
    }.toJSDictionary
  }

  $q.all(js.Array(
    $http.get[String](specificationUrl).asInstanceOf[QPromise],
    $http.get[String](poUrl).asInstanceOf[QPromise]
  )).then(successCallback = {data: js.Any =>
    data match {
      case array: js.Array[js.Dynamic] =>
        implicit val i18n = new JSObjectTranslationProvider(array(1).data)
        contextJS = new ContextJS()
        val parser = FormSpecificationParser()
        val formSpecification = parser(array(0).data.asInstanceOf[String]).get
        _form = new FormInstanceJS[DslFormSpecification](formSpecification, $scope)
        _fields = form.fields.map{f =>
          (f.name -> f).asInstanceOf[Tuple2[String, Field[_]]]
        }.toMap.toJSDictionary

        initializeFields
    }
    data
  }, errorCallback = {data: js.Any =>
  })

  var _form: FormInstanceJS[FormSpecification] = new FormInstanceJS[EmptyFormSpecification.type ](EmptyFormSpecification, $scope)

 implicit override def form: FormInstanceJS[_] = _form

  var _fields: Dictionary[Field[_]] = Dictionary.empty

  override def fields: Dictionary[Field[_]] = _fields
}