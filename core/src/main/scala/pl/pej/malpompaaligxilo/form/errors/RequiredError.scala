package pl.pej.malpompaaligxilo.form.errors

import pl.pej.malpompaaligxilo.form.{Field, FormError}
import pl.pej.malpompaaligxilo.util.{NoI18nString, I18nableString}


case object RequiredError extends FormError {
  override def message: I18nableString = NoI18nString("This field is required")
}