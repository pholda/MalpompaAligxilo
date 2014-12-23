package pl.pej.malpompaligxilo.util

import org.scalajs.jquery.JQuery
import org.scalajs.jquery.jQuery

trait HTMLable {
  def toJQuery: JQuery

  implicit def string2jQuery(str: String): JQuery = jQuery(str)
}
