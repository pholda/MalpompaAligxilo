package pl.pholda.malpompaaligxilo.dsl.parser

import pl.pholda.malpompaaligxilo.util.{DateCompanion, Date}

import scala.util.parsing.combinator.syntactical.StandardTokenParsers

trait UtilParsers extends StandardTokenParsers {
  protected implicit val dateCompanion: DateCompanion

  lexical.reserved += ("true", "false")

  def booleanLit: Parser[Boolean] = ("true" ^^^ true) | ("false" ^^^ false)
  
  def intLit: Parser[Int] = numericLit ^^ {_.toInt}

  def dateLit: Parser[Date] = stringLit ^^ dateCompanion.fromString
}
