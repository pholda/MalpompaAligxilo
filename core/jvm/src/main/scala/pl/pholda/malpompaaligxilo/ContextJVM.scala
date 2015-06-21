package pl.pholda.malpompaaligxilo

import pl.pholda.malpompaaligxilo.i18n.I18n
import pl.pholda.malpompaaligxilo.util.{DateCompanion, DateJVM}

case class ContextJVM(implicit i18n: I18n) extends Context {
  override def date: DateCompanion = DateJVM
}
