package pl.pholda.malpompaaligxilo

import pl.pholda.malpompaaligxilo.i18n.I18n
import pl.pholda.malpompaaligxilo.util.{DateCompanion, DateJS}

case class ContextJS(i18n: I18n) extends Context {
  override def date: DateCompanion = DateJS
}
