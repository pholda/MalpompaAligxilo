package pl.pholda.malpompaaligxilo.form

import pl.pholda.malpompaaligxilo.ContextJVM

class FormInstanceJVM[+T <: FormSpecification](
  val specification: T,
  rawFieldValue: Field[_] => Seq[String]
)(
  implicit val context: ContextJVM
  ) extends FormInstance(specification) {

//  TODO
  override def getRawFieldValue(field: Field[_]): Seq[String] = rawFieldValue(field)

  //TODO
  def isFilled: Boolean = false
}
