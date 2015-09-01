package pl.pholda.malpompaaligxilo.dsl.parser

import pl.pholda.malpompaaligxilo.i18n.{TranslationProvider, I18nString, NoI18nString}

import scala.util.parsing.combinator.syntactical.StandardTokenParsers

trait I18nParser extends StandardTokenParsers {

  implicit val translationProvider: TranslationProvider

  lexical.delimiters += ("@")

  def i18nString: Parser[I18nString] = singularI18nStringRef | (stringLit ^^ NoI18nString) //TODO other i18n string references

  protected[dsl] def singularI18nStringRef = "@" ~> stringLit ^^ translationProvider.t
}
