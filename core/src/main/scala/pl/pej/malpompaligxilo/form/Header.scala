package pl.pej.malpompaligxilo.form

import pl.pej.malpompaligxilo.util.I18nableString

@deprecated
case class Header(text: I18nableString, description: Option[I18nableString] = None) extends FormElement
