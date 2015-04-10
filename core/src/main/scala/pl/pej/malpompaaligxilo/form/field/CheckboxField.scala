package pl.pej.malpompaaligxilo.form.field

import pl.pej.malpompaaligxilo.form.FieldType


case class CheckboxField(default: Boolean = false) extends FieldType[Boolean] {

  override def parse(values: Seq[String]): Option[Boolean] = {
    Some(values.nonEmpty)
  }

  override val arrayValue: Boolean = false
}
