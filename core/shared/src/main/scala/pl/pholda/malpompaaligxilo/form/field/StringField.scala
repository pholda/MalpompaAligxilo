package pl.pholda.malpompaaligxilo.form.field

import pl.pholda.malpompaaligxilo.form._

case class StringField(
  textarea: Boolean = false,
  default: Option[String] = None
                        ) extends FieldType[String] {

  override def parse(values: Seq[String]): Option[String] = values.headOption

  override val arrayValue: Boolean = false

  override def separatedValues(value: Option[String]): List[(String, String)] = value match {
    case Some(v) => ("" -> v) :: Nil
    case _ => Nil
  }
}
