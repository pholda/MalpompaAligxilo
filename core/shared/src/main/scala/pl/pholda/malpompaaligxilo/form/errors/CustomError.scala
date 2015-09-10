package pl.pholda.malpompaaligxilo.form.errors

import pl.pholda.malpompaaligxilo.form.FormError
import pl.pholda.malpompaaligxilo.i18n.{I18nString, TranslationProvider, I18nableString}

case class CustomError(message: I18nableString) extends FormError {
  override def i18nMsg(implicit translationProvider: TranslationProvider): I18nString = message
}
