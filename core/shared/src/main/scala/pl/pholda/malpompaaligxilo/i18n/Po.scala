package pl.pholda.malpompaaligxilo.i18n

abstract class Po {
  def t(singular : String) : String

  def tf(singular : String, argsx: Any*): String = {
    t(singular).format(argsx)
  }
}

object Po {
//  implicit def po2GeneralPo(po: Po): Po = {
//    new Po{
//      override def t(singular: String): String = po.t(singular)
//    }
//  }
}
