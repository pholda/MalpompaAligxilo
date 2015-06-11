package pl.pholda.malpompaaligxilo.i18n

import java.io.File

import pl.pholda.malpompaaligxilo.util._

import scala.io.Source

case class PoCfg(languages: Map[Lang, Po])

object PoCfg {

  lazy val empty = new PoCfg(Map.empty)
}
