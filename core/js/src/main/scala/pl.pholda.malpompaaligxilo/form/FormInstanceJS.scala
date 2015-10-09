package pl.pholda.malpompaaligxilo.form

import pl.pholda.malpompaaligxilo.ContextJS
import pl.pholda.malpompaaligxilo.angular.FormScope
import pl.pholda.malpompaaligxilo.form.field.{DateField, CheckboxTableField, CheckboxField}

import scala.scalajs.js
import scala.util.Try

class FormInstanceJS[+T <: FormSpecification](
  specification: T,
  scope: FormScope
                      )(
  implicit val context: ContextJS
  ) extends FormInstance(specification) {

  override def getRawFieldValue(field: Field[_]): Seq[String] = {
    field.`type`.arrayValue match {
        //TODO check this part
      case true =>
        field.`type` match {
          case CheckboxTableField(rows, cols, _, default) =>
            val selected = for {
              row <- rows
              col <- cols
            } yield {
              scope.fieldValue.get(field.name) match {
                case Some(dict) =>
                  dict.asInstanceOf[js.Dictionary[Boolean]].getOrElse(s"${row.id}-${col.id}", false) match {
                    case true =>
                      Some(s"${row.id}-${col.id}")
                    case _ =>
                      None
                  }
                case _ =>
                  None
              }
            }
            selected.flatten
          case _ =>
            Seq.empty
        }
      case false =>
        field.`type` match {
          case CheckboxField(default) =>
            scope.fieldValue.get(field.name) match {
              case Some(true) =>
                Seq("true")
              case _ =>
                Seq.empty
            }
          case _ =>
            scope.fieldValue.get(field.name).map(x => Seq(x.toString)).getOrElse(Seq.empty)
        }
    }

  }
}
