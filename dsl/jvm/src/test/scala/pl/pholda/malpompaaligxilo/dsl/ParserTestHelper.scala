package pl.pholda.malpompaaligxilo.dsl

import scala.util.parsing.combinator.syntactical.StandardTokenParsers

trait ParserTestHelper[A <: StandardTokenParsers]{
  val parsers: A

  def quickParse[T](parser: parsers.Parser[T], input: String): T = {
    parser.apply(new parsers.lexical.Scanner(input)) match {
      case parsers.Success(result, _) => result
    }
  }
}
