package pl.pej.malpompaligxilo.form

import pl.pej.malpompaligxilo.util.{HTMLable, ErrorMsg, I18nString}

abstract class Field[T] extends HTMLable {
  def caption: I18nString

  def name: String

  def validate: T => Option[ErrorMsg]
}
