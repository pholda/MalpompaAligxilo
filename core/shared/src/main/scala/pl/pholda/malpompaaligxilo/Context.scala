package pl.pholda.malpompaaligxilo

import pl.pholda.malpompaaligxilo.i18n.TranslationProvider
import pl.pholda.malpompaaligxilo.util.DateCompanion

abstract class Context {
  def date: DateCompanion

  implicit def translationProvider: TranslationProvider
}

