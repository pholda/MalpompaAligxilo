package pl.pholda.malpompaaligxilo.form.field

import pl.pholda.malpompaaligxilo.form._
import pl.pholda.malpompaaligxilo.form.field.CheckboxTableField.Result
import pl.pholda.malpompaaligxilo.i18n.I18nableString

case class CheckboxTableField(
  rows: List[CheckboxTableRow],
  cols: List[CheckboxTableCol],
  disabled: List[(String, String)] = Nil,
  default: Boolean = false
                               ) extends FieldType[CheckboxTableField.Result] {

  override def parse(values: Seq[String]): Option[CheckboxTableField.Result] = {
      val l = for (row <- rows; col <- cols if values contains s"${row.id}-${col.id}") yield
        row -> col
      Some(CheckboxTableField.Result(l.toSet))
  }

  def isDisabled(row: CheckboxTableRow, col: CheckboxTableCol): Boolean = {
    disabled contains (row.id -> col.id)
  }

  override val arrayValue: Boolean = true

  override def separatedValues(value: Option[Result]): List[(String, String)] = {
    value match {
      case Some(result) if cols.size == 1 =>
        result.values.toList.map{
          case (row, col) => s"${row.id}" -> "x"
        }
      case Some(result) if rows.size == 1 =>
        result.values.toList.map{
          case (row, col) => s"${col.id}" -> "x"
        }
      case Some(result) =>
        result.values.toList.map{
          case (row, col) => s"${row.id}-${col.id}" -> "x"
        }
      case None => Nil
    }
  }
}

object CheckboxTableField {
  implicit def tuple2col(t: (String, I18nableString)): CheckboxTableCol = CheckboxTableCol(t._1, t._2)
  implicit def tuple2row(t: (String, I18nableString)): CheckboxTableRow = CheckboxTableRow(t._1, t._2)

  case class Result(values: Set[(CheckboxTableRow, CheckboxTableCol)]) {
    lazy val selectedInTotal: Int = values.size
  }
}
