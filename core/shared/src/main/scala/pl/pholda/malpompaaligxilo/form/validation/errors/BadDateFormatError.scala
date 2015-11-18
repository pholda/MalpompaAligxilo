package pl.pholda.malpompaaligxilo.form.validation.errors

import pl.pholda.malpompaaligxilo.form.FormError
import pl.pholda.malpompaaligxilo.i18n.{TranslationProvider, I18nString}

case object BadDateFormatError extends FormError {
  override def i18nMsg(implicit translationProvider: TranslationProvider): I18nString =
    translationProvider.t("_bad_date_format_error")
}
