package pl.pholda.malpompaaligxilo.form.action

import pl.pholda.malpompaaligxilo.form.{FormAction, FormInstance}
import pl.pholda.malpompaaligxilo.googleapi.{Worksheet, Spreadsheet}

case class AddToGoogleSpreadsheetFormAction(worksheet: Worksheet) extends FormAction {

  override def run(formInstance: FormInstance[_]): Unit = {
    lazy val data: Map[String, String] = formInstance.fields.filter(_.store).flatMap{field =>
      field.separatedValues(formInstance)
    }.toMap

    worksheet.insertRow(data.map{
      case (k, v) =>
        k.replace('/', '-') -> v
    })
  }
}