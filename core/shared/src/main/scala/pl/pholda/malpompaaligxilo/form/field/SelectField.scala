package pl.pholda.malpompaaligxilo.form.field

import pl.pholda.malpompaaligxilo.form.errors.NothingSelectedError
import pl.pholda.malpompaaligxilo.form.{FieldType, FormError}

import scala.collection.immutable
import scala.util.Try

case class SelectField(
  options: List[EnumOption],
  size: Int = 1,
  notSelected: Option[EnumOption] = None,
  ordering: Option[EnumOptionOrdering] = None
                        ) extends FieldType[EnumOption] {

  lazy val allOptionsIndexed = (notSelected match {
    case Some(o) => o :: options
    case None => options
  }).zipWithIndex.map{case (option, idx) => idx->option}.toMap

  override def parse(values: Seq[String]): Option[EnumOption] = {
    Try(values.headOption.flatMap(allOptionsIndexed get _.toInt)).toOption.flatten
  }

  override def validate(t: EnumOption): FieldValidationResult = {
    notSelected match {
      case Some(ns) if ns.value == t.value => FailureFieldValidation(NothingSelectedError)
      case _ => SuccessFieldValidation
    }
  }

  def getOptionIndex(option: EnumOption): Int = {
    allOptionsIndexed.find(_._2 == option).map(_._1).getOrElse(-1)
  }

  def getNotSelectedIndex: Option[Int] = {
    notSelected.map { option =>
      allOptionsIndexed.find(_._2 == option).map(_._1).getOrElse(-1)
    }
  }

  override val arrayValue: Boolean = false
}
