package pl.pej.malpompaaligxilo.form

import pl.pej.malpompaaligxilo.form.field.{CalculateField, TableCheckBoxField, CustomCalculateField}
import pl.pej.malpompaaligxilo.util.Dates

import scala.collection.immutable.ListMap
//import scala.scalajs.js.annotation.{JSExportAll, JSExport}
import scala.util.Try

//@JSExportAll
abstract class Form {

  def id: String

  protected def getRawFieldValue(fieldName: FieldName): Seq[String]

  def dates: Dates

  def fields: List[Field[_]]

  def fieldValue[T](field: Field[T]): Option[T] = {
    field.`type` match {
      case cf: CalculateField[_] =>
        cf.evaluate(this)
      case _ =>
        field.parse(getRawFieldValue(field.name).filterNot(_.isEmpty))
    }
  }

  def getFieldValue[T](field: Field[T]): T = {
    fieldValue(field).get
  }

  def hasErrors: Boolean = {
    fields.exists(_.validate(this).nonEmpty)
  }

//  def validate: ValidationResult = {
//    val errors = fields.map{ f =>
//      f -> f.validate(fieldValue(f), this)
//    }.collect{
//      case (k, Some(v)) => k -> v
//    }
//
//    if (errors.isEmpty) {
//      SuccessValidation
//    } else {
//      FailureValidation(errors.toMap)
//    }
//  }
}
