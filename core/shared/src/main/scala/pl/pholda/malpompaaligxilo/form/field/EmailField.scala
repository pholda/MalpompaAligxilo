package pl.pholda.malpompaaligxilo.form.field

import pl.pholda.malpompaaligxilo.form._

case object EmailField extends FieldType[String] {

  override def parse(values: Seq[String]): Option[String] = values.headOption

  override val arrayValue: Boolean = false
}
