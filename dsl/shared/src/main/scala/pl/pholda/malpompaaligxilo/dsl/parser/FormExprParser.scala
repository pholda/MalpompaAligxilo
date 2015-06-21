package pl.pholda.malpompaaligxilo.dsl.parser

import pl.pholda.malpompaaligxilo.dsl.DslFormExpr
import pl.pholda.malpompaaligxilo.i18n.I18nString

import scala.util.parsing.combinator.syntactical.StandardTokenParsers

trait FormExprParser extends StandardTokenParsers with I18nParser {
  import DslFormExpr._

  lexical.reserved += ("value", "valueOpt" ,"$$")

  lexical.delimiters += ("(", ")", "{", "}", "$", "=", "+")

  def formExpr: Parser[DslFormExpr[_]] = "{" ~> expr <~ "}"

  protected[dsl] def innerExpr: Parser[DslFormExpr[_]] =
    fieldValue | compare | literal | stringConcat | i18nExpr

  protected[dsl] def i18nExpr = i18nString ^^ {
    Literal[I18nString]
  }

  protected[dsl] def expr = innerExpr | ("{"~>innerExpr<~"}")

  protected[dsl] def fieldValueOpt: Parser[DslFormExpr[Option[Any]]] = ("valueOpt"|"$$")  ~> stringLit  ^^ {
    FieldValueOpt
  }

  protected[dsl] def fieldValue: Parser[DslFormExpr[Any]] = ("value"|"$") ~> stringLit ^^ {
    FieldValue
  }

  protected[dsl] def literal: Parser[Literal[_]] =
    "true" ^^^ Literal(true) |
    "false" ^^^ Literal(false) |
    stringLit ^^ {Literal(_)} |
    numericLit ^^ {Literal(_)}

  protected[dsl] def compare: Parser[DslFormExpr[Boolean]] = "(" ~> (expr <~ "=") ~ expr <~ ")" ^^ {
    case a ~ b => Compare(a, b)
  }

  protected[dsl] def stringConcat: Parser[StringConcat] = ("(" ~> expr <~ "+") ~ rep1sep(expr, "+") <~ ")" ^^ {
    case a~b => StringConcat(a::b:_*)
  }
}
