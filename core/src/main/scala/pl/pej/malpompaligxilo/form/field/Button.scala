package pl.pej.malpompaligxilo.form.field

import org.scalajs.jquery.JQuery
import pl.pej.malpompaligxilo.form.{FormExpression, Field}
import pl.pej.malpompaligxilo.util.I18nString

case class Button(name: String, caption: I18nString, expression: FormExpression[Unit]) extends Field[Nothing] {
  override def toJQuery: JQuery = s"""<button>${caption("eo")}</button> """
}
