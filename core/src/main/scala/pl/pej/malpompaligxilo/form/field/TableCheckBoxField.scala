package pl.pej.malpompaligxilo.form.field

import org.scalajs.jquery.{JQuery, jQuery}
import pl.pej.malpompaligxilo.form._
import pl.pej.malpompaligxilo.util.I18nableString

import scala.util.hashing.Hashing.Default

case class TableCheckBoxField(
  rows: List[TableCheckBoxRow],
  cols: List[TableCheckBoxCol],
  disabled: List[(String, String)] = Nil,
  default: Boolean = false
                               ) extends FieldType[Set[(TableCheckBoxRow, TableCheckBoxCol)]] {

  override def toJQuery(field: Field[Set[(TableCheckBoxRow, TableCheckBoxCol)]]): JQuery = {
    val table = jQuery("<table />")

    if (cols.size > 1) {
      val rowHead = jQuery("""<tr class="head" />""").append("<td />")
      cols.foreach{
        case TableCheckBoxCol(id, caption) =>
          rowHead.append(s"""<th>${caption("eo")}</th>""")
      }

      table.append(rowHead)
    }

    rows.foreach{
      case TableCheckBoxRow(rid, rcaption) =>
        val row = jQuery("""<tr />""")
        row append s"""<td>${rcaption("eo")}</td>"""
        cols.foreach{
          case TableCheckBoxCol(cid, _) =>
            row append s"""<td><input type="checkbox" ${
              if (disabled.contains((rid, cid))) """disabled="disabled" """ else ""
            }${
              if (default && !disabled.contains((rid, cid)))
                """checked="checked" """ else ""
            }name="${field.name}[]" value="${if (cols.size > 1) s"$rid-$cid" else rid}" /></td>"""
        }
        table append row
    }

    table
  }

  override def parse(values: Seq[String]): Option[Set[(TableCheckBoxRow, TableCheckBoxCol)]] = {
    val l = for (row <- rows; col <- cols if values contains s"${row.id}-${col.id}") yield
      row -> col
    Some(l.toSet)
  }
}

object TableCheckBoxField {
  implicit def tuple2col(t: (String, I18nableString)): TableCheckBoxCol = TableCheckBoxCol(t._1, t._2)
  implicit def tuple2row(t: (String, I18nableString)): TableCheckBoxRow = TableCheckBoxRow(t._1, t._2)
}
