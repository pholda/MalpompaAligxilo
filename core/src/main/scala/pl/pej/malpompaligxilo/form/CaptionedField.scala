package pl.pej.malpompaligxilo.form

import org.scalajs.jquery.JQuery
import pl.pej.malpompaligxilo.util.I18nableString

case class CaptionedField[T](caption: I18nableString, description: Option[I18nableString], field: Field[T]) extends Field[T] {
  override def name: String = field.name

  override def toJQuery: JQuery = field.toJQuery
}
