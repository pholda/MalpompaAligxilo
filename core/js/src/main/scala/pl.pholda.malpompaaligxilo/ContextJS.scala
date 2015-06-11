package pl.pholda.malpompaaligxilo

import pl.pholda.malpompaaligxilo.util.{JSDate, DateCompanion}

object ContextJS extends Context {
  override def date: DateCompanion = JSDate
}
