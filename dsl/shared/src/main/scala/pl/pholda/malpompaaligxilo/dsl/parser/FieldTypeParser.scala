package pl.pholda.malpompaaligxilo.dsl.parser

import pl.pholda.malpompaaligxilo.dsl.DslFormExpr
import pl.pholda.malpompaaligxilo.form.field.{CheckboxField, CustomCalculateField, IntField, StringField}
import pl.pholda.malpompaaligxilo.form.{FieldType, FormExpr}
import scala.util.parsing.combinator.syntactical.StandardTokenParsers

trait FieldTypeParser extends StandardTokenParsers with UtilParsers with FormExprParser {

  lexical.reserved += ("type", "string", "multiline", "default", "int", "min", "max", "step",
    "checkbox", "calculable")

  lexical.delimiters += ("=", "(", ")")

  def fieldType: Parser[(String, FieldType[_])] = "type" ~> "=" ~> (
      string | int | calculable | checkbox
    ) ^^ {"type" -> _}

  protected[dsl] def string =
    "string" ~> opt("multiline") ~ opt("default" ~ "(" ~> stringLit <~ ")") ^^ {
      case multiline ~ default =>
        StringField(multiline.isDefined, default)
    }

  protected[dsl] def int = "int" ~> opt("min" ~> intLit) ~ opt("max" ~> intLit) ~ opt("step" ~> intLit) ^^ {
    case min ~ max ~ step =>
      IntField(min, max, step)
  }

  protected[dsl] def calculable = "calculable" ~> formExpr ^^ {
    case expr: DslFormExpr[_] =>
      CustomCalculateField(FormExpr{form =>
        expr(form) match {
          case x: Option[Any] =>
            x
          case x => Option(x)
        }
      })
  }

  protected[dsl] def checkbox = "checkbox" ~> opt("default" ~> booleanLit) ^^ {
    case default =>
      CheckboxField(default.getOrElse(true))
  }

//  protected[dsl] def dateField = "date" ~> opt("min" ~> date)
}
