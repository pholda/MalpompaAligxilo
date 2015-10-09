package pl.pholda.malpompaaligxilo.form.field

import pl.pholda.malpompaaligxilo.i18n.NoTranslations
import pl.pholda.malpompaaligxilo.{TestFormJVM, Context, ContextJVM}

object CostFieldJVMTest extends CostFieldTest with TestFormJVM {
  implicit def translations = NoTranslations

//  override implicit def context: Context = ContextJVM()
}
