package pl.pej.malpompaligxilo.form.errors

import pl.pej.malpompaligxilo.form.{Field, FormError}
import pl.pej.malpompaligxilo.util.{NoI18nString, I18nableString}


case object RequiredError extends FormError {
  override def message: I18nableString = NoI18nString("vi devas plenigi tiun ĉi kampon")
}