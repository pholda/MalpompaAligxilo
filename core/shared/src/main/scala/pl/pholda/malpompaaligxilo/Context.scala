package pl.pholda.malpompaaligxilo

import pl.pholda.malpompaaligxilo.i18n.I18n
import pl.pholda.malpompaaligxilo.util.DateCompanion

abstract class Context {
  def date: DateCompanion

  implicit def i18n: I18n
}

