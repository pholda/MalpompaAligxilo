package pl.pholda.malpompaaligxilo.form

import pl.pholda.malpompaaligxilo.{ContextJVM, Context}
import pl.pholda.malpompaaligxilo.util.DateCompanion

class FormInstanceJVM[T <: FormSpecification](
  val specification: T
)(
  implicit val context: ContextJVM
  ) extends FormInstance(specification) {

  //TODO
  override protected def getRawFieldValue(field: Field[_]): Seq[String] = Seq()


  //TODO
  def isFilled: Boolean = false
}