package pl.pej.malpompaaligxilo.form.field

import pl.pej.malpompaaligxilo.form._
import pl.pej.malpompaaligxilo.util.I18nableString

case class TableCheckboxField(
  rows: List[TableCheckboxRow],
  cols: List[TableCheckboxCol],
  disabled: List[(String, String)] = Nil,
  default: Boolean = false
                               ) extends FieldType[Set[(TableCheckboxRow, TableCheckboxCol)]] {

  override def parse(values: Seq[String]): Option[Set[(TableCheckboxRow, TableCheckboxCol)]] = {
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

  override val arrayValue: Boolean = true
}

object TableCheckboxField {
  implicit def tuple2col(t: (String, I18nableString)): TableCheckboxCol = TableCheckboxCol(t._1, t._2)
  implicit def tuple2row(t: (String, I18nableString)): TableCheckboxRow = TableCheckboxRow(t._1, t._2)
}
