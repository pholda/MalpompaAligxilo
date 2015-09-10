package pl.pholda.malpompaaligxilo.dsl.parser

import pl.pholda.malpompaaligxilo.Context
import pl.pholda.malpompaaligxilo.dsl.DslFormExpr
import pl.pholda.malpompaaligxilo.dsl.parser.expr.FormExprParser
import pl.pholda.malpompaaligxilo.form.field._
import pl.pholda.malpompaaligxilo.form.{FieldType, FormExpr}
import scala.util.parsing.combinator.syntactical.StandardTokenParsers

trait FieldTypeParser extends StandardTokenParsers with UtilParsers with FormExprParser {
  implicit val context: Context

  lexical.reserved += ("type", "string", "multiline", "default", "int", "min", "max", "step",
    "checkbox", "computed", "date", "email", "select", "size", "notSelected", "orderBy",
    "caption", "value", "checkboxTable", "rows", "cols", "disabled", "default")

  lexical.delimiters += ("=", "(", ")", "<", ">", "{", "}", ",")

  def fieldType: Parser[FieldType[_]] = "type" ~> "=" ~> (
      stringField | intField | computedField | checkboxField | dateField | emailField |
      selectField | checkboxTableField
    )

  protected[dsl] def stringField =
    "string" ~> opt("multiline") ~ opt("default" ~ "(" ~> stringLit <~ ")") ^^ {
      case multiline ~ default =>
        StringField(multiline.isDefined, default)
    }

  protected[dsl] def emailField = "email" ^^^ EmailField

  protected[dsl] def intField = "int" ~> opt("min" ~> intLit) ~ opt("max" ~> intLit) ~ opt("step" ~> intLit) ^^ {
    case min ~ max ~ step =>
      IntField(min, max, step)
  }

  protected[dsl] def computedField = "computed" ~> formExpr ^^ {
    case expr: DslFormExpr[_] =>
      CustomComputeField(FormExpr{ form =>
        expr(form) match {
          case x: Option[Any] =>
            x
          case x => Option(x)
        }
      })
  }

  protected[dsl] def checkboxField = "checkbox" ~> opt("default" ~ "(" ~> booleanLit <~ ")") ^^ {
    case default =>
      CheckboxField(default.getOrElse(true))
  }

  protected[dsl] def dateField = "date" ~> opt("min" ~> dateLit) ~ opt("max" ~> dateLit) ^^ {
    case min ~ max =>
      DateField(min, max)
  }
  
  protected[dsl] def selectField = "select" ~> opt("size" ~> intLit) ~
    ("{" ~> rep1sep(selectOption, opt(",")) <~ "}") ~
    opt("notSelected" ~> selectOption) ~ opt("orderBy" ~> ("caption" | "value")) ^^ {
    case size ~ options ~ notSelected ~ ordering =>
      ordering match {
        case None =>
          SelectField(options, size.getOrElse(1), notSelected, None)
        case Some(str) if str == "caption" =>
          SelectField(options, size.getOrElse(1), notSelected, Some(EnumOptionOrdering.byCaption))
        case Some(str) if str == "value" =>
          SelectField(options, size.getOrElse(1), notSelected, Some(EnumOptionOrdering.byValue))
        case Some(x) =>
          throw new Exception(s"unkown ordering $x, ${x.getClass}")
      }
  }

  protected[dsl] def selectOption = "<" ~> stringLit ~ i18nString <~ ">" ^^ {
    case value ~ name =>
      EnumOption(value, name)
  }

  protected[dsl] def checkboxTableField = "checkboxTable" ~>
    ("rows" ~> ("{" ~> rep1sep(checkboxTableRow, opt(",")) <~ "}")) ~
    ("cols" ~> ("{" ~> rep1sep(checkboxTableCol, opt(",")) <~ "}")) ~
    opt("disabled" ~> "{" ~> rep1sep(checkboxTableCell, opt(",")) <~ "}") ~
    opt("default" /*~ "("*/ ~> booleanLit /*<~ ")"*/) ^^ {
    case rows ~ cols ~ disabled ~ default =>
      CheckboxTableField(rows, cols, disabled.getOrElse(Nil), default.getOrElse(false))
  }

  protected[dsl] def checkboxTableRow: Parser[CheckboxTableRow] = "<" ~> stringLit ~ i18nString <~ ">" ^^ {
    case id ~ caption =>
      CheckboxTableRow(id, caption)
  }

  protected[dsl] def checkboxTableCol = "<" ~> stringLit ~ i18nString <~ ">" ^^ {
    case id ~ caption =>
      CheckboxTableCol(id, caption)
  }

  protected[dsl] def checkboxTableCell = "<" ~> stringLit ~ stringLit <~ ">" ^^ {
    case row ~ col => row -> col
  }
}