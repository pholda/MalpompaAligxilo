package pl.pholda.malpompaaligxilo.form

import pl.pholda.malpompaaligxilo.Context
import pl.pholda.malpompaaligxilo.form.field.CalculateField
import pl.pholda.malpompaaligxilo.util.DateCompanion

abstract class FormInstance(specification: FormSpecification) {
  def id: String = specification.id

  def context: Context

  def dates: DateCompanion = context.date

  protected def getRawFieldValue(field: Field[_]): Seq[String]

  def fields: List[Field[_]] = specification.fields

  def fieldValue[T](field: Field[T]): Option[T] = {
    field.`type` match {
      case cf: CalculateField[_] =>
        cf.evaluate(this)
      case _ =>
        field.parse(getRawFieldValue(field).filterNot(_.isEmpty))
    }
  }

  def getFieldValue[T](field: Field[T]): T = {
    fieldValue(field).get
  }
}
