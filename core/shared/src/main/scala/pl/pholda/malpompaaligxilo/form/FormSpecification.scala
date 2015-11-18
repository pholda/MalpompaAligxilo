package pl.pholda.malpompaaligxilo.form

abstract class FormSpecification {
  def fields: List[Field[_]]

  def id: String
  lazy val elementsByName: Map[FieldName, Field[_]] = fields.map(e => e.name -> e).toMap
}