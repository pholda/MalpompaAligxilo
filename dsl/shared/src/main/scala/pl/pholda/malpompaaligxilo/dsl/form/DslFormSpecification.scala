package pl.pholda.malpompaaligxilo.dsl.form

import pl.pholda.malpompaaligxilo.form.{FieldName, Field, FormSpecification}

case class DslFormSpecification(elements: List[DslFormElement], id: String) extends FormSpecification {
  lazy val fields: List[Field[_]] = elements//.flatMap(_.fields)
}
