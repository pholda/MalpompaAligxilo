package pl.pholda.malpompaaligxilo.dsl.parser.expr

import pl.pholda.malpompaaligxilo.dsl.DslFormExpr
import pl.pholda.malpompaaligxilo.dsl.expr.logical.{Logical2, Negation}
import pl.pholda.malpompaaligxilo.dsl.expr.math.Operation2
import pl.pholda.malpompaaligxilo.dsl.parser.UtilParsers

import scala.util.parsing.combinator.PackratParsers
import scala.util.parsing.combinator.syntactical.StandardTokenParsers

trait LogicExprParser extends StandardTokenParsers with UtilParsers with PackratParsers {

  lexical.reserved +=(">", ">=", "<", "<=", "!=", "!")

  lexical.delimiters +=("(", ")")

  //fix for scalajs :(
  lexical.reserved foreach {
    lexical.delimiters += _
  }

  lazy val logicExpr: PackratParser[DslFormExpr[_]] = negation //| greaterThan

  protected[dsl] def negation = "!" ~> expr ^^ {
    case a => Negation(a)
  }

//  protected[dsl] def greaterThan = (expr <~ ">") ~ expr ^^ {
//    case a~b => Logical2(a, b){
//      case (d1: Double, d2: Double) => d1 > d2
//    }
//  }
}
