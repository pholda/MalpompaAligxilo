package pl.pej.malpompaligxilo.form

import pl.pej.malpompaligxilo.form.field.{TableCheckBoxField, CalculateField}
import pl.pej.util.Dates

import scala.util.Try

abstract class Form {

  def id: String

  protected def getRawFieldValue(fieldName: FieldName): Seq[String]

  def dates: Dates

  def fields: List[Field[_]]

  @deprecated("pass to getFieldValue the field")
  def getFieldValue(fieldName: FieldName): Option[Any] = {
    val x = fields.find(_.name == fieldName).getOrElse(throw new Exception(s"field not found $fieldName"))
    x.parse(getRawFieldValue(fieldName))
  }

  def getFieldValue[T](field: Field[T]): Option[T] = {
    field.parse(getRawFieldValue(field.name))
  }

  def validate(data: Map[String, Option[Any]]): ValidationResult = {
    val errors = fields.collect{
      case f: Field[_] =>
        f -> f.validate(data(f.name), this)
    }.collect{
      case (f, Some(error)) => f -> error
    }
    if (errors.isEmpty) {
      SuccessValidation
    } else {
      FailureValidation(errors.toMap)
    }
  }

  def parse(post: Map[String, Seq[String]]): Map[String, Option[Any]] = {
    val post2 = post.map{
      case (k, v) if k.endsWith("[]") => k.stripSuffix("[]") -> (if (v == Seq("")) Seq.empty else v)
      case (k, v) => k -> (if (v == Seq("")) Seq.empty else v)
    }.toMap

    fields.collect{
      case Field(name: String, _, cf: CalculateField[_], _, _, _, _, _, _) =>
        name -> Try(cf.formula(this)).toOption
      case f: Field[_] =>
        f.name -> f.parse(post2.getOrElse(f.name, Seq.empty))
    }.toMap
  }
}
