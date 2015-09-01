package pl.pholda.malpompaaligxilo.form

import com.sun.xml.internal.ws.resources.ClientMessages
import pl.pholda.malpompaaligxilo.Context
import pl.pholda.malpompaaligxilo.form.field.ComputeField
import pl.pholda.malpompaaligxilo.util.DateCompanion

abstract class FormInstance(specification: FormSpecification) {
  def id: String = specification.id

  def context: Context

  def dates: DateCompanion = context.date

  protected def getRawFieldValue(field: Field[_]): Seq[String]

  def fields: List[Field[_]] = specification.fields

  lazy val fieldsByName: Map[String, Field[_]] = fields.map(f => f.name -> f).toMap

  def fieldValue[T](field: Field[T]): Option[T] = {
    field.`type` match {
      case cf: ComputeField[_] =>
        cf.evaluate(this)
      case _ =>
        field.parse(getRawFieldValue(field).filterNot(_.isEmpty))
    }
  }

  def getFieldValue[T](field: Field[T]): T = {
    fieldValue(field).get
  }

  def validate: ValidationResult = {
    val errors = fields.flatMap{field =>
      field.validate(this).map{
        field -> _
      }
    }
    if (errors.isEmpty) {
      SuccessValidation
    } else {
      FailureValidation(errors.toMap)
    }
  }
}
