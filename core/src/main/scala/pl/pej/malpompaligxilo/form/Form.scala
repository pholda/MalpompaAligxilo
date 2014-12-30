package pl.pej.malpompaligxilo.form

import pl.pej.malpompaligxilo.form.field.CalculateField
import pl.pej.util.Dates

import scala.util.Try

case class Form(
  id: String,
  getRawFieldValue: FieldName => Seq[String],
  dates: Dates,
  elements: FormElement*) {
lazy val fields = {
    val fields = elements.collect{
      case f: Field[_] => f
    }.map(f => f.name -> f)
    Map(fields:_*)
  }

  def getFieldValue(fieldName: FieldName): Option[Any] = {
    fields(fieldName).parse(getRawFieldValue(fieldName))
  }

  def validate(data: Map[String, Option[Any]]): ValidationResult = {
    val errors = elements.collect{
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

    elements.collect{
      case Field(name: String, _, cf: CalculateField[_], _, _, _, _, _, _) =>
        name -> Try(cf.formula(this)).toOption
      case f: Field[_] =>
        f.name -> f.parse(post2.getOrElse(f.name, Seq.empty))
    }.toMap
  }
}
