package pl.pholda.malpompaaligxilo.form.field

import pl.pholda.malpompaaligxilo.form.errors.NothingSelectedError
import pl.pholda.malpompaaligxilo.form.{FieldType, FormError}

import scala.collection.immutable

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
    values.headOption.flatMap(allOptionsIndexed get _.toInt)
//    values.headOption.flatMap(v => allOptions.find(_.value == v)).orElse(notSelected)
  }

  override def validate(t: EnumOption): Option[FormError] = {
    notSelected match {
      case Some(ns) if ns.value == t.value => Some(NothingSelectedError)
      case _ => None
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
