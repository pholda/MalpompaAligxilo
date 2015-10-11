package pl.pholda.malpompaaligxilo.dsl.parser

import pl.pholda.malpompaaligxilo.dsl.DslFormExpr
import pl.pholda.malpompaaligxilo.dsl.expr.form.ContainerSet
import pl.pholda.malpompaaligxilo.dsl.parser.expr.FormSpecStdTokenParsers
import pl.pholda.malpompaaligxilo.util.{Date, DateCompanion}

import scala.util.parsing.combinator.PackratParsers
import scala.util.parsing.combinator.syntactical.StandardTokenParsers
import scala.util.parsing.combinator.token.StdTokens

trait UtilParsers extends FormSpecStdTokenParsers with PackratParsers with StdTokens {
  protected implicit val dateCompanion: DateCompanion

  lexical.reserved +=("true", "false", "set")

  lexical.delimiters +=("[", "]", ",")

  protected[dsl] def expr: PackratParser[DslFormExpr[_]]

  protected[dsl] def booleanLit: Parser[Boolean] = ("true" ^^^ true) | ("false" ^^^ false)

  protected[dsl] def intLit: Parser[Int] = numericLit ^^ {
    _.toInt
  }

  protected[dsl] def doubleLit: Parser[Double] =
    elem("number", _.isInstanceOf[NumericLit]) ^^ (_.chars.toDouble)

  protected[dsl] def dateLit: Parser[Date] = stringLit ^^ dateCompanion.fromString

  protected[dsl] def container = "set" ~> "[" ~> repsep(expr, ",") <~ "]" ^^ {
    case elements => ContainerSet(elements)
  }
}
