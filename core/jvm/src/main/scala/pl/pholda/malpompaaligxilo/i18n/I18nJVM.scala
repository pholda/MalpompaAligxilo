package pl.pholda.malpompaaligxilo.i18n

import java.io.File

import scala.io.Source

object I18nJVM {
  implicit def scaposerPo2po(scaposerPo: scaposer.Po): Po =
    new scaposer.Po(scaposerPo.body) with Po

  def fromFiles(languages: (Lang, File)*): I18nPo = {
    val translations = languages.map{
      case (lang, file) =>
        lang -> scaposer.Parser.parsePo(
          Source.fromFile(file).mkString
        )
    }.collect{
      case (lang, Some(po)) => lang -> scaposerPo2po(po)
    }.toMap

    new I18nPo(PoSet(translations))
  }

  def fromResources(clazz: Class[_], languages: (Lang, String)*): I18nPo = {
    val translations = languages.map{
      case (lang, resource) =>
        lang -> scaposer.Parser.parsePo(
          Source.fromInputStream(clazz.getResourceAsStream(resource)).mkString
        )
    }.collect{
      case (lang, Some(po)) => lang -> scaposerPo2po(po)
    }.toMap

    new I18nPo(PoSet(translations))
  }
}
