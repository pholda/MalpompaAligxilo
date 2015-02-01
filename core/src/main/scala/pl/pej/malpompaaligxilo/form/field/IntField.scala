package pl.pej.malpompaaligxilo.form.field

import pl.pej.malpompaaligxilo.form.FieldType

import scala.util.Try


case class IntField(min: Option[Int] = None, max: Option[Int] = None, step: Option[Int] = None) extends FieldType[Int] {

  override def parse(values: Seq[String]): Option[Int] = values.headOption.flatMap(s => Try{s.toInt}.toOption)
}
