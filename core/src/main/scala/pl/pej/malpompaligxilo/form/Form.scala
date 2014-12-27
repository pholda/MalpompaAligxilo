package pl.pej.malpompaligxilo.form

import pl.pej.malpompaligxilo.util.ToJQueryable
import org.scalajs.jquery.{JQuery, jQuery}

/**
 * describas a pl.pej.malpompaaligxilo.form
 * @param fields
 */
case class Form(id: String, action: String, formElementFormatter: FormElementFormatter)(elements: FormElement*) extends ToJQueryable {
  def getFieldValue(name: String): Any = jQuery(s"#$id [name=$name]").`val`()

  override def toJQuery: JQuery = {
    val form = jQuery(
      s"""
        |<form id="$id" method="post" action="$action">
        |</form>
      """.stripMargin)
    elements.foreach{element =>
      form.append(formElementFormatter(element))
    }
    form.append("""<input type="submit" value="sendu" name="send" />""")
    form
  }
}
