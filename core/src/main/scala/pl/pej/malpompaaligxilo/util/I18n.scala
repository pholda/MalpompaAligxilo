package pl.pej.malpompaaligxilo.util

import scala.io.Source

object I18n {
  def po(msgid: String)(implicit poCfg: PoCfg): I18nPoString = {
    new I18nPoString(
      poCfg.languages.mapValues{po =>
      {i: Int => po.t(msgid/*, "", i*/)}
      }
    )
  }

  //TODO add context, plurar etc.
  def t(msgid: String)(implicit poCfg: PoCfg, lang: Lang): String = {
    try {
      poCfg.languages(lang).t(msgid)
    } catch {
      case e: Exception => throw new Exception(s"unable to translate $msgid to language $lang")
    }
  }

  def tf(msgid: String, args: Any*)(implicit poCfg: PoCfg, lang: Lang): String = {
    poCfg.languages(lang).tf(msgid, args:_*)
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
