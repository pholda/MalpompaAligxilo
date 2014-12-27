//package pl.pej.malpompaligxilo.form
//
//import org.scalajs.jquery.{JQuery, jQuery}
//
//case class FieldGroup(/*horizontal: Boolean = true*/)(fields: Field[_]*) extends FieldType[Nothing] {
//  override def toJQuery(field: Field[Nothing]): JQuery = {
//    val jq = jQuery()
//    fields.foreach{f =>
//      jQuery("<div />").append(f.toJQuery).appendTo(jq)
//    }
//    jq
//  }
//
//}
