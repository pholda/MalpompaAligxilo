package pl.pej.malpompaaligxilo.form.action

import pl.pej.malpompaaligxilo.form.field.{EnumOption, TableCheckboxCol, TableCheckboxRow, TableCheckboxField}
import pl.pej.malpompaaligxilo.form.{Form, FormAction}
import pl.pej.malpompaaligxilo.googleapi.Spreadsheet
import pl.pej.malpompaaligxilo.util.Date

case class AddToGoogleSpreadsheetFormAction(spreadsheet: Spreadsheet, worksheetTitle: String) extends FormAction {

  override def run(form: Form): Unit = {
    val data: Map[String, String] = form.fields.flatMap{field =>
      field.value(form) match {
        case Some(v) => v match {
          case s: String => (field.name -> s) :: Nil
          case true => (field.name -> "x") :: Nil
          case false => Nil
          case x if field.`type`.isInstanceOf[TableCheckboxField] =>
            val s = x.asInstanceOf[Set[(TableCheckboxRow, TableCheckboxCol)]]
            s.toList.map{
              case (row, col) => s"${field.name}-${row.id}-${col.id}" -> "x"
            }
          case EnumOption(enumValue, _) => (field.name -> enumValue) :: Nil
          case d: Date => (field.name -> d.toString) :: Nil
          case x => (field.name -> x.toString) :: Nil
        }
        case None => Nil
      }
    }.toMap

    spreadsheet.insertRow(data, worksheetTitle)
  }
}