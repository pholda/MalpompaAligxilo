package pl.pholda.malpompaaligxilo.angular

import pl.pholda.malpompaaligxilo.form.{Field, FormSpecification}

object EmptyFormSpecification extends FormSpecification {
  override def fields: List[Field[_]] = Nil

  override def id: String = ""
}