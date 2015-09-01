package pl.pholda.malpompaaligxilo.dsl.parser.expr

import pl.pholda.malpompaaligxilo.dsl.DslFormExpr
import pl.pholda.malpompaaligxilo.dsl.expr.form.MapPattern.SimplePattern
import pl.pholda.malpompaaligxilo.dsl.expr.form._
import pl.pholda.malpompaaligxilo.dsl.parser.I18nParser
import pl.pholda.malpompaaligxilo.i18n.I18nString

import scala.util.parsing.combinator.syntactical.StandardTokenParsers

trait FormExprParser extends StandardTokenParsers with I18nParser with DateExprParser {

  lexical.reserved += ("value", "valueOpt" ,"mapValue", "enumValue", "enumCaption", "default")

  lexical.delimiters += ("(", ")", "{", "}", "$", "=", "+", "$$", "=>")

  def formExpr: Parser[DslFormExpr[_]] = "{" ~> expr <~ "}"

  override protected[dsl] def innerExpr: Parser[DslFormExpr[_]] =
    fieldValue/* | fieldValueOpt*/ | compare | literal | stringConcat | i18nExpr | date | map |
      enumValue | enumCaption

  protected[dsl] def i18nExpr = i18nString ^^ {
    Literal[I18nString]
  }

  protected[dsl] def expr = innerExpr | ("{"~>innerExpr<~"}")

//  protected[dsl] def fieldValueOpt: Parser[DslFormExpr[Option[Any]]] = ("valueOpt"|"$$")  ~> stringLit  ^^ {
//    FieldValueOpt
//  }

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

  protected[dsl] def map: Parser[Map] =
    ("mapValue" ~> "(" ~> innerExpr <~ ")") ~ ("{" ~> rep1sep(mapCase, opt(","))) <~ "}" ^^ {
      case value ~ cases =>
        Map(value, cases:_*)
    }

  protected[dsl] def mapCase: Parser[MapCase] = (mapPattern <~ "=>") ~ mapExpression ^^ {
    case pattern ~ expression =>
      MapCase(pattern, expression)
  }

  protected[dsl] def mapPattern = simplePattern | defaultPattern

  protected[dsl] def simplePattern: Parser[SimplePattern] = innerExpr ^^ {case e =>
    MapPattern.SimplePattern(e)
  }

  protected[dsl] def defaultPattern = "default" ^^^ MapPattern.DefaultPattern

  protected[dsl] def mapExpression = innerExpr ^^ {
    MapExpression(_)
  }

  protected[dsl] def enumValue = "enumValue" ~> "(" ~> innerExpr <~ ")" ^^ EnumValue

  protected[dsl] def enumCaption = "enumCaption" ~> "(" ~> innerExpr <~ ")" ^^ EnumCaption
}
