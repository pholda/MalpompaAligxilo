package pl.pholda.malpompaaligxilo.form

import pl.pholda.malpompaaligxilo.i18n.{TranslationProvider, I18nString}

abstract class FormError {
  def i18nMsg(implicit translationProvider: TranslationProvider): I18nString
}
