package pl.pholda.malpompaaligxilo.form.field

import pl.pholda.malpompaaligxilo.form._
import pl.pholda.malpompaaligxilo.i18n.I18nableString

case class CheckboxTableField(
  rows: List[CheckboxTableRow],
  cols: List[CheckboxTableCol],
  disabled: List[(String, String)] = Nil,
  default: Boolean = false
                               ) extends FieldType[Set[(CheckboxTableRow, CheckboxTableCol)]] {

  override def parse(values: Seq[String]): Option[Set[(CheckboxTableRow, CheckboxTableCol)]] = {
    if (cols.size == 1 ) {
      val l = for (row <- rows if values contains s"${row.id}") yield
        row -> cols.head
      Some(l.toSet)
    } else {
      val l = for (row <- rows; col <- cols if values contains s"${row.id}-${col.id}") yield
        row -> col
      Some(l.toSet)
    }
  }

  def isDisabled(row: CheckboxTableRow, col: CheckboxTableCol): Boolean = {
    disabled contains (row.id -> col.id)
  }

  override val arrayValue: Boolean = true
}

object CheckboxTableField {
  implicit def tuple2col(t: (String, I18nableString)): CheckboxTableCol = CheckboxTableCol(t._1, t._2)
  implicit def tuple2row(t: (String, I18nableString)): CheckboxTableRow = CheckboxTableRow(t._1, t._2)
}
