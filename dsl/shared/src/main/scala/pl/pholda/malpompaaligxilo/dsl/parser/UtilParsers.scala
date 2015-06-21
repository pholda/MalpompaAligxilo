package pl.pholda.malpompaaligxilo.dsl.parser

import scala.util.parsing.combinator.syntactical.StandardTokenParsers

trait UtilParsers extends StandardTokenParsers {
  lexical.reserved += ("true", "false")

  def booleanLit: Parser[Boolean] = ("true" ^^^ true) | ("false" ^^^ false)
  
  def intLit: Parser[Int] = numericLit ^^ {_.toInt}

//  def date: Parser[DateJVM] = {
//
//  }
}
