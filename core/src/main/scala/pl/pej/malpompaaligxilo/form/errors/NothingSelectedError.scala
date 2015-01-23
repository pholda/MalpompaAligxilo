package pl.pej.malpompaaligxilo.form.errors

import pl.pej.malpompaaligxilo.form.FormError
import pl.pej.malpompaaligxilo.util.{I18nString, I18nableString}

object NothingSelectedError extends FormError {
  override def message: I18nableString = I18nString(
    "eo" -> "vi devas elekti ion"
  )
}
