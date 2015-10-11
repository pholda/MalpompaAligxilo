package pl.pholda.malpompaaligxilo.dsl.parser

import scala.util.parsing.combinator.lexical.StdLexical

class FormSpecificationLexical extends StdLexical {
  override def token: Parser[Token] = {
    {
      digit ~ rep(digit) ~ '.' ~ digit ~ rep(digit) ^^ {
        case first ~ rest ~ _ ~ fracFirst ~ fracRest =>
          NumericLit(first :: rest ::: '.' :: fracFirst :: fracRest mkString "")
      }
    } | super.token
  }
}
