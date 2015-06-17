package pl.pholda.malpompaaligxilo.form.field

import pl.pholda.malpompaaligxilo.form._

import scala.util.Try

abstract class CalculateField[T] extends FieldType[T] {
  protected def formula: FormExpr[Option[T]]

  def evaluate(implicit formInstance: FormInstance): Option[T] = Try(formula(formInstance)).toOption.flatten

  override def parse(values: Seq[String]): Option[T] = None

  override val arrayValue: Boolean = false
}