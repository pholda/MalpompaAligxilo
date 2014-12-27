package pl.pej.malpompaligxilo.form.field

import org.scalajs.jquery.{JQuery, jQuery}
import pl.pej.malpompaligxilo.form._
import pl.pej.malpompaligxilo.util.I18nableString

import scala.util.hashing.Hashing.Default

case class TableCheckBoxField(
  rows: List[(String, I18nableString)],
  cols: List[(String, I18nableString)],
  disabled: List[(String, String)] = Nil,
  default: Boolean = false
                               ) extends FieldType[Array[Array[Boolean]]] {

  override def toJQuery(field: Field[Array[Array[Boolean]]]): JQuery = {
    val table = jQuery("<table />")

    if (cols.size > 1) {
      val rowHead = jQuery("""<tr class="head" />""").append("<td />")
      cols.foreach{
        case (id, caption) =>
          rowHead.append(s"""<th>${caption("eo")}</th>""")
      }

      table.append(rowHead)
    }

    rows.foreach{
      case (rid, rcaption) =>
        val row = jQuery("""<tr />""")
        row append s"""<td>${rcaption("eo")}</td>"""
        cols.foreach{
          case (cid, _) =>
            row append s"""<td><input type="checkbox" ${
              if (disabled.contains((rid, cid))) """disabled="disabled" """ else ""
            }${
              if (default && !disabled.contains((rid, cid)))
                """checked="checked" """ else ""
            }name="${field.name}-$rid-$cid" /></td>"""
        }
        table append row
    }

    table
  }
}
