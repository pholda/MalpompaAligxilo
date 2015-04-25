package pl.pej.malpompaaligxilo.util

import scaposer.Po

import scala.io.Source

abstract class GeneralPo {
  def t(singular : String) : String

  def tf(singular : String, argsx: Any*): String = {
    t(singular).format(argsx)
  }
}

object GeneralPo {
  implicit def po2GeneralPo(po: Po): GeneralPo = {
    new GeneralPo{
      override def t(singular: String): String = po.t(singular)
    }
  }
}
