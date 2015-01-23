package pl.pej.malpompaaligxilo.form.field

import org.scalajs.jquery.JQuery
import pl.pej.malpompaaligxilo.form.{Field, FieldType}

import scala.util.Try


case class IntField(min: Option[Int] = None, max: Option[Int] = None, step: Option[Int] = None) extends FieldType[Int] {
  import pl.pej.malpompaaligxilo.util.ToJQueryable.string2jQuery

  override def toJQuery(field: Field[Int]): JQuery =
    s"""<input type="number" name="${field.name}" ${
      min match {
        case Some(n) => s"""min="$n" """
        case _ => ""
      }
    }${
      max match {
        case Some(n) => s"""max="$n" """
        case _ => ""
      }
    }${
      step match {
        case Some(n) => s"""step="$n" """
        case _ => ""
      }
    }placeholder="${field.placeholder.map(_("eo")).getOrElse("")}" />"""

  override def parse(values: Seq[String]): Option[Int] = values.headOption.flatMap(s => Try{s.toInt}.toOption)
}
