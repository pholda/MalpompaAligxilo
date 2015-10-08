package pl.pholda.malpompaaligxilo.form.field

import pl.pholda.malpompaaligxilo.form.FieldType


case class CheckboxField(default: Boolean = false) extends FieldType[Boolean] {

  override def parse(values: Seq[String]): Option[Boolean] = {
    Some(values.nonEmpty)
  }

  override val arrayValue: Boolean = false

  override def separatedValues(value: Option[Boolean]): List[(String, String)] = value match {
    case Some(true) => "" -> "x" :: Nil
    case _ => Nil
  }
}
