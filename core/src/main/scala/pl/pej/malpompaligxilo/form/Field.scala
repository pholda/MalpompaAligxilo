package pl.pej.malpompaligxilo.form

import pl.pej.malpompaligxilo.util._

//TODO add value class for identifiers ("name")
abstract class Field[T] extends HTMLable {
  def caption: I18nableString

  def name: String

//  def placeholder: I18nString

//  def validate: T => Option[ErrorMsg]
}
