package pl.pholda.malpompaaligxilo.form.action

import pl.pholda.malpompaaligxilo.form.field._
import pl.pholda.malpompaaligxilo.form.{FormAction, FormInstance}
import pl.pholda.malpompaaligxilo.googleapi.Spreadsheet
import pl.pholda.malpompaaligxilo.i18n.I18nableString
import pl.pholda.malpompaaligxilo.util.Date

import scala.util.Try

case class AddToGoogleSpreadsheetFormAction(spreadsheet: Spreadsheet, worksheetTitle: String) extends FormAction {

  override def run(formInstance: FormInstance): Unit = {
    lazy val data: Map[String, String] = formInstance.fields.filter(_.store).flatMap{field =>
      val x = field.separatedValues(formInstance) match {
        case None =>
          field.value(formInstance) match {
          case Some(v) => v match {
            case i18n: I18nableString =>
              (field.name -> Try(i18n("")).getOrElse("(translation not found)")) :: Nil
            case s: String =>
              (field.name -> s) :: Nil
            case true =>
              (field.name -> "x") :: Nil
            case false =>
              Nil
            case result: CheckboxTableField.Result =>
              val checkboxTableField = field.`type`.asInstanceOf[CheckboxTableField]

              if (checkboxTableField.cols.size == 1) {
                result.values.toList.map{
                  case (row, col) => s"${field.name}-${row.id}" -> "x"
                }
              } else if (checkboxTableField.rows.size == 1) {
                result.values.toList.map{
                  case (row, col) => s"${field.name}-${col.id}" -> "x"
                }
              } else {
                result.values.toList.map{
                  case (row, col) => s"${field.name}-${row.id}-${col.id}" -> "x"
                }
              }
            case EnumOption(enumValue, _) => (field.name -> enumValue) :: Nil
            case d: Date => (field.name -> d.toString) :: Nil
            case other => (field.name -> other.toString) :: Nil
          }
          case None => Nil
        }
        case Some(values) =>
          values
      }
      x
    }.toMap

    spreadsheet.insertRow(data.map{
      case (k, v) =>
        k.replace('/', '-') -> v
    }.toMap, worksheetTitle)
  }
}