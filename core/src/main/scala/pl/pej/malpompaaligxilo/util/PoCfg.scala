package pl.pej.malpompaaligxilo.util

import java.io.File

import scala.io.Source
import pl.pej.malpompaaligxilo.util.GeneralPo.po2GeneralPo

import scala.scalajs.js
import scala.scalajs.js.annotation.JSName

case class PoCfg(languages: Map[Lang, GeneralPo])

object PoCfg {
  def fromFiles(languages: (Lang, File)*): PoCfg = {
    PoCfg(languages.map{
      case (lang, file) =>
        lang -> scaposer.Parser.parsePo(Source.fromFile(file).mkString)
    }.collect{
      case (lang, Some(po)) => lang -> po2GeneralPo(po)
    }.toMap)
  }

  def fromResources(clazz: Class[_], languages: (Lang, String)*): PoCfg = {
    PoCfg(languages.map{
      case (lang, resource) =>
        lang -> scaposer.Parser.parsePo(
          Source.fromInputStream(clazz.getResourceAsStream(resource)).mkString
        )
    }.collect{
      case (lang, Some(po)) => lang -> po2GeneralPo(po)
    }.toMap)
  }

  def fromJS(variable: String)(implicit lang: Lang): PoCfg = {
    PoCfg(Map(
      lang -> new GeneralPo {
        override def t(singular: String): String =
          js.eval(s"""$variable.$lang["$singular"]""").toString
      }
    ))
  }

  lazy val empty = new PoCfg(Map.empty)
}
