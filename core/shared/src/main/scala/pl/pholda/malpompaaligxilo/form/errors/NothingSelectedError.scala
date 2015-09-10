package pl.pholda.malpompaaligxilo.form.errors

import pl.pholda.malpompaaligxilo.form.FormError
import pl.pholda.malpompaaligxilo.i18n.{I18nString, TranslationProvider}

case object NothingSelectedError extends FormError {
  override def i18nMsg(implicit translationProvider: TranslationProvider): I18nString =
    translationProvider.t("_nothing_selected")
}
