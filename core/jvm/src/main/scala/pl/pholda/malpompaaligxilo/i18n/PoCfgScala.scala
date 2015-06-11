package pl.pholda.malpompaaligxilo.i18n

import java.io.File
import pl.pholda.malpompaaligxilo.util._
import scala.io.Source

object PoCfgScala {
  implicit def sPo2po(sPo: scaposer.Po): Po = {
    new Po {
      override def t(singular: String): String = sPo.t(singular)
    }
  }

  def fromFiles(languages: (Lang, File)*): PoCfg = {
    PoCfg(languages.map{
      case (lang, file) =>
        lang -> scaposer.Parser.parsePo(Source.fromFile(file).mkString)
    }.collect{
      case (lang, Some(po)) => lang -> sPo2po(po)
    }.toMap)
  }

  def fromResources(clazz: Class[_], languages: (Lang, String)*): PoCfg = {
    PoCfg(languages.map{
      case (lang, resource) =>
        lang -> scaposer.Parser.parsePo(
          Source.fromInputStream(clazz.getResourceAsStream(resource)).mkString
        )
    }.collect{
      case (lang, Some(po)) => lang -> sPo2po(po)
    }.toMap)
  }

  def getSimpleBodyFromResource(clazz: Class[_], resource: String): Map[String, String] = {
    val po =scaposer.Parser.parsePo(Source.fromInputStream(clazz.getResourceAsStream(resource)).mkString).getOrElse(
      throw new Exception(s"resource $resource not found")
    )
    po.body.map{
      case ((_, s), seq) => s -> seq.head
    }
  }
}