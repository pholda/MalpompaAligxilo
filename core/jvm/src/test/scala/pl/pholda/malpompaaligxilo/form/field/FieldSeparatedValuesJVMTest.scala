package pl.pholda.malpompaaligxilo.form.field

import pl.pholda.malpompaaligxilo.i18n.NoTranslations
import pl.pholda.malpompaaligxilo.{ContextJVM, Context}

object FieldSeparatedValuesJVMTest extends FieldSeparatedValuesTest {
  implicit def translations = NoTranslations

  override implicit def context: Context = ContextJVM()
}
