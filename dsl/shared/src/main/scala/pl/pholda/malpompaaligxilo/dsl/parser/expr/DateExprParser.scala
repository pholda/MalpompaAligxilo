package pl.pholda.malpompaaligxilo.dsl.parser.expr

import pl.pholda.malpompaaligxilo.dsl.DslFormExpr
import pl.pholda.malpompaaligxilo.dsl.expr.date._
import pl.pholda.malpompaaligxilo.dsl.parser.UtilParsers
import pl.pholda.malpompaaligxilo.util.Date

import scala.util.parsing.combinator.syntactical.StandardTokenParsers

trait DateExprParser extends StandardTokenParsers with UtilParsers {

  lexical.reserved += ("date", "dateDiff", "years", "months", "days",
    "compareDates", "today", "dayOfWeek")

  lexical.delimiters += ("(", ")", ",", "<", "<=", "=", ">", ">=", "!=")

  def date: PackratParser[DslFormExpr[_]] = dateFromString | dateDiff | dateCompare | dateToday

  protected[dsl] def dateToday: Parser[DslFormExpr[Date]] = "date" ~ "(" ~ "today" ~ ")" ^^^ DateToday

  protected[dsl] def dateFromString: Parser[DslFormExpr[Date]] = "date" ~> "(" ~> expr <~ ")" ^^ {
    DateFromString
  }

  protected[dsl] def dateDiff: Parser[DslFormExpr[Int]] =
    ("dateDiff" ~> "(" ~> ("years" | "months" | "days") <~ ",") ~
      expr ~ ("," ~> expr <~ ")") ^^ {
      case unit ~ from ~ to => DateDiff(unit, from, to)
    }

  protected[dsl] def dateCompare: PackratParser[DslFormExpr[Boolean]] =
    "compareDates" ~> "(" ~> expr ~ ("<" | "<=" | "=" | ">" | ">=" | "!=") ~ expr <~ ")" ^^ {
      case a ~ op ~ b => DateCompare(op, a, b)
    }

  protected[dsl] def dateOfWeek: Parser[DslFormExpr[Int]] =
    "dayOfWeek" ~> "(" ~> expr <~ ")" ^^ DayOfWeek
}