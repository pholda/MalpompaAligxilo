package pl.pholda.malpompaaligxilo

import pl.pholda.malpompaaligxilo.i18n.{TranslationProvider, I18n}
import pl.pholda.malpompaaligxilo.util.{DateCompanion, DateJS}

case class ContextJS(implicit translationProvider: TranslationProvider) extends Context {
  override def date: DateCompanion = DateJS
}
