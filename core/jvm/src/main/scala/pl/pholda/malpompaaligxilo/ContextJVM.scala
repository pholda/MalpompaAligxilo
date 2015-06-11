package pl.pholda.malpompaaligxilo

import pl.pholda.malpompaaligxilo.util.{DateCompanion, JVMDate}

object ContextJVM extends Context {
  override def date: DateCompanion = JVMDate
}