package pl.pholda.malpompaaligxilo.form

abstract class FormSpecification {
  def fields: List[Field[_]]

  def id: String
}
