package pl.pej.malpompaligxilo.form

import pl.pej.util.Dates

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

  def getFieldValue: FieldName => Option[Any] = {field =>
    fields(field).parse(getRawFieldValue(field))
  }

  def validate(data: Map[String, Option[Any]]): ValidationResult = {
    val errors = elements.collect{
      case f: Field[_] =>
        f -> f.validate(data(f.name))
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
      case f: Field[_] =>
        f.name -> f.parse(post2.getOrElse(f.name, Seq.empty))
    }.toMap
  }
}
