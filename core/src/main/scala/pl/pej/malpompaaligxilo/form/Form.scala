package pl.pej.malpompaaligxilo.form

import pl.pej.malpompaaligxilo.form.field.{TableCheckBoxField, CalculateField}
import pl.pej.malpompaaligxilo.util.Dates

import scala.collection.immutable.ListMap
import scala.util.Try

abstract class Form {

  def id: String

  protected def getRawFieldValue(fieldName: FieldName): Seq[String]

  def dates: Dates

  def fields: List[Field[_]]

  @deprecated("pass to getFieldValue the field")
  def getFieldValue(fieldName: FieldName): Option[Any] = {
    val x = fields.find(_.name == fieldName).getOrElse(throw new Exception(s"field not found $fieldName"))
    x.parse(getRawFieldValue(fieldName))
  }

  def getFieldValue[T](field: Field[T]): Option[T] = {
    field.parse(getRawFieldValue(field.name))
  }

  def parse(post: Map[String, Seq[String]]): ParsedForm = {
    val post2 = post.map{
      case (k, v) if k.endsWith("[]") => k.stripSuffix("[]") -> (if (v == Seq("")) Seq.empty else v)
      case (k, v) => k -> (if (v == Seq("")) Seq.empty else v)
    }.toMap

    val data = fields.collect{
      case f@Field(_, _, cf: CalculateField[_], _, _, _, _, _, _) =>
        FilledField(f, Try(cf.formula(this)).toOption, None)
      case f@Field(_, _, _, _, _, _, _, _, _) =>
        val value = f.parse(post2.getOrElse(f.name, Seq.empty))
        FilledField(f, value, f.validate(value, this))
    }
    ParsedForm(this, data)
  }
}
