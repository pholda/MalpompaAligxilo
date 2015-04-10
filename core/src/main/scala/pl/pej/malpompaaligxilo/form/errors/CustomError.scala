package pl.pej.malpompaaligxilo.form.errors

import pl.pej.malpompaaligxilo.form.FormError
import pl.pej.malpompaaligxilo.util.{I18nableString, I18nString}

case class CustomError(message: I18nableString) extends FormError
