package pl.pholda.malpompaaligxilo.i18n

import pl.pholda.malpompaaligxilo.util._

import scala.scalajs.js

object PoCfgJS {
  def fromJS(variable: String)(implicit lang: Lang): PoCfg = {
    PoCfg(Map(
      lang -> new Po {
        override def t(singular: String): String =
          js.eval(s"""$variable.$lang["$singular"]""").toString
      }
    ))
  }
}
