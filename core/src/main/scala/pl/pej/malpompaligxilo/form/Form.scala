package pl.pej.malpompaligxilo.form

case class Form(
  id: String,
  getFieldValue: FieldName => Any,
  elements: FormElement*) {

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
