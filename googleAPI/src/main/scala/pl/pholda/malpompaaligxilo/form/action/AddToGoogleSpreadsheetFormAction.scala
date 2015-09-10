package pl.pholda.malpompaaligxilo.form.action

import pl.pholda.malpompaaligxilo.form.field.{EnumOption, TableCheckboxCol, TableCheckboxField, TableCheckboxRow}
import pl.pholda.malpompaaligxilo.form.{FormAction, FormInstance}
import pl.pholda.malpompaaligxilo.googleapi.Spreadsheet
import pl.pholda.malpompaaligxilo.util.Date

case class AddToGoogleSpreadsheetFormAction(spreadsheet: Spreadsheet, worksheetTitle: String) extends FormAction {

  override def run(formInstance: FormInstance): Unit = {
    val data: Map[String, String] = formInstance.fields.filter(_.store).flatMap{field =>
      field.separatedValues(formInstance) match {
        case None =>
          field.value(formInstance) match {
          case Some(v) => v match {
            case s: String => (field.name -> s) :: Nil
            case true => (field.name -> "x") :: Nil
            case false => Nil
            case x if field.`type`.isInstanceOf[TableCheckboxField] =>
              val s = x.asInstanceOf[Set[(TableCheckboxRow, TableCheckboxCol)]]

              if (field.`type`.asInstanceOf[TableCheckboxField].cols.size == 1) {
                s.toList.map{
                  case (row, col) => s"${field.name}-${row.id}" -> "x"
                }
              } else if (field.`type`.asInstanceOf[TableCheckboxField].rows.size == 1) {
                s.toList.map{
                  case (row, col) => s"${field.name}-${col.id}" -> "x"
                }
              } else {
                s.toList.map{
                  case (row, col) => s"${field.name}-${row.id}-${col.id}" -> "x"
                }
              }
            case EnumOption(enumValue, _) => (field.name -> enumValue) :: Nil
            case d: Date => (field.name -> d.toString) :: Nil
            case x => (field.name -> x.toString) :: Nil
          }
          case None => Nil
        }
        case Some(values) =>
          values
      }
    }.toMap

    spreadsheet.insertRow(data.map{
      case (k, v) => k.replace('/', '-') -> v
    }.toMap, worksheetTitle)
  }
}