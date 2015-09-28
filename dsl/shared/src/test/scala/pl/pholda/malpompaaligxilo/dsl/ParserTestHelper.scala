package pl.pholda.malpompaaligxilo.dsl

import pl.pholda.malpompaaligxilo.dsl.form.DslFormSpecification

import scala.util.parsing.combinator.PackratParsers
import scala.util.parsing.combinator.syntactical.StandardTokenParsers

trait ParserTestHelper[A <: StandardTokenParsers with PackratParsers] {
  val parsers: A

  def quickParse[T](parser: parsers.Parser[T], input: String): T = {

    val result = parsers.phrase(parser)(new parsers.PackratReader(new parsers.lexical.Scanner(input))) match {
      case parsers.Success(result, _) => Some(result)
      case parsers.Failure(msg, next) =>
        throw new Exception(msg)
      case _ => None
    }

    result.get
  }
}
