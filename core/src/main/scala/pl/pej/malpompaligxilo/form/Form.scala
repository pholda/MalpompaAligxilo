package pl.pej.malpompaligxilo.form

import pl.pej.malpompaligxilo.util.HTMLable
import org.scalajs.jquery.{JQuery, jQuery}

/**
 * describas a pl.pej.malpompaaligxilo.form
 * @param fields
 */
case class Form(id: String, action: String, fieldFormatter: FieldFormatter, fields: Field[_]*) extends HTMLable {
  def getFieldValue(name: String): Any = jQuery(s"#$id [name=$name]").`val`()

  override def toJQuery: JQuery = {
    val form = jQuery(
      s"""
        |<form id="$id" method="post" action="$action">
        |</form>
      """.stripMargin)
    fields.foreach{field =>
      form.append(fieldFormatter(field))
    }
    form.append("""<input type="submit" value="sendu" />""")
    form
  }
}
