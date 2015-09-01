package pl.pholda.malpompaaligxilo

import pl.pholda.malpompaaligxilo.i18n.TranslationProvider
import pl.pholda.malpompaaligxilo.util.{DateCompanion, DateJVM}

case class ContextJVM(implicit translationProvider: TranslationProvider) extends Context {
  override def date: DateCompanion = DateJVM
}
