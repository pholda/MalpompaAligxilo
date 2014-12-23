package pl.pej.malpompaligxilo.form

import org.scalajs.jquery.JQuery
import pl.pej.malpompaligxilo.util._

//TODO add value class for identifiers ("name")
abstract class Field[T] extends HTMLable {
//  def caption: I18nableString

  def name: String

  def toJQuery: JQuery

//  def placeholder: I18nString

//  def validate: T => Option[ErrorMsg]

  def captioned(caption: I18nableString, description: Option[I18nableString] = None): CaptionedField[T] = {
    CaptionedField(caption, description, this)
  }

  def @#(caption: I18nableString): CaptionedField[T] = captioned(caption)

  def @##(captionAndDescription: (I18nableString, I18nableString)): CaptionedField[T] =
    captioned(captionAndDescription._1, Option(captionAndDescription._2))
  def @##(caption: I18nableString, description: I18nableString): CaptionedField[T] =
    captioned(caption, Option(description))
}

object Field {

}