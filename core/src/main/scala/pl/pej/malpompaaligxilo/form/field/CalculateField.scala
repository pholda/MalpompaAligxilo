package pl.pej.malpompaaligxilo.form.field

import pl.pej.malpompaaligxilo.form.{FieldType, FormExpr}

case class CalculateField[T](
  formula: FormExpr[T]
                              ) extends FieldType[T] {

  override def parse(values: Seq[String]): Option[T] = None
}
