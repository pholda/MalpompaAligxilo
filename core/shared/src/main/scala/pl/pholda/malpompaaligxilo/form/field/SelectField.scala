package pl.pholda.malpompaaligxilo.form.field

import pl.pholda.malpompaaligxilo.form.validation.errors.NothingSelectedError
import pl.pholda.malpompaaligxilo.form.{FieldType, FormError}

import scala.collection.immutable
import scala.util.Try

case class SelectField(
  options: List[EnumOption],
  size: Int = 1,
  notSelected: Option[EnumOption] = None,
  ordering: Option[EnumOptionOrdering] = None
                        ) extends FieldType[EnumOption] {

//  lazy val allOptionsIndexed = /*(notSelected match {
//    case Some(o) => o :: options
//    case None => */options
//  /*})*/.zipWithIndex.map{case (option, idx) => idx->option}.toMap

  override def parse(values: Seq[String]): Option[EnumOption] = {
    values.headOption.flatMap(value =>
      options.find(_.value == value)
    )
  }

  override def validate(t: EnumOption): FieldValidationResult = {
    notSelected match {
      case Some(ns) if ns.value == t.value => FailureFieldValidation(NothingSelectedError)
      case _ => SuccessFieldValidation
    }
  }

  override val arrayValue: Boolean = false

  override def separatedValues(value: Option[EnumOption]): List[(String, String)] = {
    value match {
      case Some(selected) =>
        "" -> selected.value :: Nil
      case _ =>
        Nil
    }
  }
}
