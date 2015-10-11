package pl.pholda.malpompaaligxilo.dsl.parser

import scala.util.parsing.combinator.lexical.StdLexical
import scala.util.parsing.combinator.token

class FormSpecificationLexical extends StdLexical {

  case class RealNumericLit(chars: String) extends Token

  override def token: Parser[Token] = {
    {
      digit ~ rep(digit) ~ '.' ~ digit ~ rep(digit) ^^ {
        case first ~ rest ~ _ ~ fracFirst ~ fracRest =>
          RealNumericLit(first :: rest ::: '.' :: fracFirst :: fracRest mkString "")
      }
    } | super.token
  }
}