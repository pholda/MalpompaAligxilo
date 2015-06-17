package pl.pholda.malpompaaligxilo

import pl.pholda.malpompaaligxilo.i18n.I18nPo
import pl.pholda.malpompaaligxilo.util.{DateCompanion, DateJVM}

case class ContextJVM(implicit i18n: I18nPo) extends Context {
  override def date: DateCompanion = DateJVM
}
