package pl.pej.malpompaaligxilo.form.field

import pl.pej.malpompaaligxilo.form._

case object EmailField extends FieldType[String] {

  override def parse(values: Seq[String]): Option[String] = values.headOption
}
