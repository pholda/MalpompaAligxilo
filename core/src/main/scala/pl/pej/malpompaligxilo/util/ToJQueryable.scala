package pl.pej.malpompaligxilo.util

import org.scalajs.jquery.JQuery
import org.scalajs.jquery.jQuery

trait ToJQueryable {
  def toJQuery: JQuery
}

object ToJQueryable {
  implicit def string2jQuery(str: String): JQuery = jQuery(str)
}
