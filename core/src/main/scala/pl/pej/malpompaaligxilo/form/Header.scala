package pl.pej.malpompaaligxilo.form

import pl.pej.malpompaaligxilo.util.I18nableString

@deprecated
case class Header(text: I18nableString, description: Option[I18nableString] = None) extends FormElement
