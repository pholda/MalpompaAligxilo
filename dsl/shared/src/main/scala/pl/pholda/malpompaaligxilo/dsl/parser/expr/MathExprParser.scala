package pl.pholda.malpompaaligxilo.dsl.parser.expr

import pl.pholda.malpompaaligxilo.dsl.DslFormExpr
import pl.pholda.malpompaaligxilo.dsl.expr.math.Operation2
import pl.pholda.malpompaaligxilo.dsl.parser.UtilParsers

import scala.util.parsing.combinator.PackratParsers
import scala.util.parsing.combinator.syntactical.StandardTokenParsers

trait MathExprParser extends StandardTokenParsers with UtilParsers with PackratParsers {

  lexical.reserved +=(/*"selectedInTotal", */"+", "-", "/", "*")

  lexical.delimiters +=("(", ")")

  //fix for scalajs :(
  lexical.reserved foreach {
    lexical.delimiters += _
  }

  lazy val mathExpr: PackratParser[DslFormExpr[_]] = add | subtract | multiply | div

  protected[dsl] def add = (expr <~ "+") ~ expr ^^ {
    case a~b => Operation2(a, b)(_ + _)
  }

  protected[dsl] def subtract = (expr <~ "-") ~ expr ^^ {
    case a~b => Operation2(a, b)(_ - _)
  }

  protected[dsl] def multiply = (expr <~ "*") ~ expr ^^ {
    case a~b => Operation2(a, b)(_ * _)
  }

  protected[dsl] def div = (expr <~ "/") ~ expr ^^ {
    case a~b => Operation2(a, b)(_ / _)
  }
}
