package pl.pej.malpompaaligxilo.form

abstract class FieldType[T] {

  def validate(t: T): Option[FormError] = None

  def parse(values: Seq[String]): Option[T]
}
