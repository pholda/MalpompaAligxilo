package pl.pholda.malpompaaligxilo.form.field

import pl.pholda.malpompaaligxilo.form._
import pl.pholda.malpompaaligxilo.i18n.I18nableString

import scala.util.Try

abstract class ComputeField[T] extends FieldType[T] {
  protected def formula: FormExpr[Option[T]]

  def evaluate(implicit formInstance: FormInstance[_]): Option[T] = Try(formula(formInstance)).toOption.flatten

  override def parse(values: Seq[String]): Option[T] = None

  override val arrayValue: Boolean = false

  override def separatedValues(value: Option[T]): List[(String, String)] = {
    value.map{v => "" -> (v match {
        case str: String => str
        case i18n: I18nableString => i18n("")
        case other => other.toString
      }) :: Nil
    }.getOrElse(Nil)
  }
}
