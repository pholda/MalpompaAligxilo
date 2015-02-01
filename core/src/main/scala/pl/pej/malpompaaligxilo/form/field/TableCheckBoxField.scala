package pl.pej.malpompaaligxilo.form.field

import pl.pej.malpompaaligxilo.form._
import pl.pej.malpompaaligxilo.util.I18nableString

case class TableCheckBoxField(
  rows: List[TableCheckBoxRow],
  cols: List[TableCheckBoxCol],
  disabled: List[(String, String)] = Nil,
  default: Boolean = false
                               ) extends FieldType[Set[(TableCheckBoxRow, TableCheckBoxCol)]] {

  override def parse(values: Seq[String]): Option[Set[(TableCheckBoxRow, TableCheckBoxCol)]] = {
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
}

object TableCheckBoxField {
  implicit def tuple2col(t: (String, I18nableString)): TableCheckBoxCol = TableCheckBoxCol(t._1, t._2)
  implicit def tuple2row(t: (String, I18nableString)): TableCheckBoxRow = TableCheckBoxRow(t._1, t._2)
}
