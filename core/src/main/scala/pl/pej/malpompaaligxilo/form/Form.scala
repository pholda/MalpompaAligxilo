package pl.pej.malpompaaligxilo.form

import pl.pej.malpompaaligxilo.form.field.CalculateField
import pl.pej.malpompaaligxilo.util.Dates

abstract class Form {

  def id: String

  @deprecated
  protected def getRawFieldValue(fieldName: FieldName): Seq[String] = {
    val field = fields.find(_.name  == fieldName)
    field.map{
      f => getRawFieldValue(f)
    }.getOrElse(Seq.empty[String])
  }

  protected def getRawFieldValue(field: Field[_]): Seq[String]

  def isFilled: Boolean

  implicit def context: Context

  implicit final def dates: Dates = context.dates

  def fields: List[Field[_]]

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

  def hasErrors: Boolean = {
    fields.exists(_.validate(this).nonEmpty)
  }
}
