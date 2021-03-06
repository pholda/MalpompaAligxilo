package pl.pholda.malpompaaligxilo.form.field

import pl.pholda.malpompaaligxilo.form.FieldType

import scala.util.Try


case class IntField(min: Option[Int] = None, max: Option[Int] = None, step: Option[Int] = None) extends FieldType[Int] {

  override def parse(values: Seq[String]): Option[Int] = values.headOption.flatMap(s => Try{s.toInt}.toOption)

  override val arrayValue: Boolean = false

  override def separatedValues(value: Option[Int]): List[(String, String)] = value match {
    case Some(v) => ("" -> v.toString) :: Nil
    case _ => Nil
  }
}
