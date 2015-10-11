package pl.pholda.malpompaaligxilo.dsl.parser.expr

import pl.pholda.malpompaaligxilo.dsl.DslFormExpr
import pl.pholda.malpompaaligxilo.dsl.expr.form.MapPattern.SimplePattern
import pl.pholda.malpompaaligxilo.dsl.expr.form._
import pl.pholda.malpompaaligxilo.dsl.parser.I18nParser
import pl.pholda.malpompaaligxilo.i18n.I18nString

import scala.util.parsing.combinator.PackratParsers
import scala.util.parsing.combinator.syntactical.StandardTokenParsers

trait FormExprParser extends FormSpecStdTokenParsers
  with I18nParser
  with DateExprParser
  with CheckboxTableExprParser
  with MathExprParser
  with LogicExprParser
  with PackratParsers {

  lexical.reserved += ("value", "valueOpt" ,"mapValue", "enumValue", "enumCaption", "default",
    "if", "else", "contains")

  lexical.delimiters += ("(", ")", "{", "}", "$", "=", ".", "$$", "=>")

  def formExpr: Parser[DslFormExpr[_]] = "{" ~> expr <~ "}"

  protected[dsl] lazy val innerExpr: PackratParser[DslFormExpr[_]] =
    logicExpr | date | mathExpr | checkboxTableExpr | ifStatement |
    compare | literal | stringConcat | contains | container | i18nExpr | map |
      enumValue | enumCaption |  fieldValue/* | fieldValueOpt*/

  protected[dsl] def i18nExpr = i18nString ^^ {
    Literal[I18nString]
  }

  override protected[dsl] lazy val expr: PackratParser[DslFormExpr[_]] = innerExpr | ("{"~>innerExpr<~"}")

  protected[dsl] def fieldValue: Parser[DslFormExpr[Any]] = ("value"|"$") ~> stringLit ^^ {
    FieldValue
  }

  protected[dsl] def literal: Parser[Literal[_]] =
    "true" ^^^ Literal(true) |
    "false" ^^^ Literal(false) |
    stringLit ^^ {Literal(_)} |
    intLit ^^ {num => Literal(num)} |
    doubleLit ^^ {Literal(_)}

  @deprecated
  protected[dsl] def compare: Parser[DslFormExpr[Boolean]] = "(" ~> (expr <~ "=") ~ expr <~ ")" ^^ {
    case a ~ b => Compare(a, b)
  }

  @deprecated
  protected[dsl] def stringConcat: Parser[StringConcat] = ("(" ~> expr <~ ".") ~ rep1sep(expr, ".") <~ ")" ^^ {
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

  protected[dsl] def ifStatement = ("if" ~> "(" ~> innerExpr <~ ")") ~
    ("{" ~> innerExpr <~ "}" ~ "else" ~ "{") ~ innerExpr <~ "}" ^^ {
    case ifExpr~trueExpr~falseExpr => IfStatement(ifExpr, trueExpr, falseExpr)
  }

  protected[dsl] def contains = (expr <~ "contains") ~ expr ^^ {
    case container~element => Contains(container, element)
  }
}
