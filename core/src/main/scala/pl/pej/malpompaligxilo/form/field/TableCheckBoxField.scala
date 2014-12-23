package pl.pej.malpompaligxilo.form.field

import org.scalajs.jquery.{JQuery, jQuery}
import pl.pej.malpompaligxilo.form.Field
import pl.pej.malpompaligxilo.util.I18nableString

case class TableCheckBoxField(
  name: String,
  caption: I18nableString,
  description: Option[I18nableString] = None,
  rows: List[(String, I18nableString)],
  cols: List[(String, I18nableString)],
  disabled: List[(String, String)] = Nil
                               ) extends Field[Array[Array[Boolean]]] {

  override def toHTML: JQuery = {
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
            }name="$name-$rid-$cid" /></td>"""
        }
        table append row
    }

    table
    jQuery(s"""<div>${caption("eo")}: </div>""").append(table)
  }
}
