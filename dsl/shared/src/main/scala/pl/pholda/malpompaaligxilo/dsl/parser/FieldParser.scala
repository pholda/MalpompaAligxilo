package pl.pholda.malpompaaligxilo.dsl.parser

import pl.pholda.malpompaaligxilo.dsl.parser.expr.FormExprParser
import pl.pholda.malpompaaligxilo.dsl.DslFormExpr
import pl.pholda.malpompaaligxilo.form._
import pl.pholda.malpompaaligxilo.i18n._

import scala.util.parsing.combinator.syntactical.StandardTokenParsers


trait FieldParser extends StandardTokenParsers
  with FormExprParser
  with FieldTypeParser
  with UtilParsers
  with I18nParser {
  lexical.reserved += ("form", "name", "store", "caption",
    "description", "placeholder",
    "visible", "required",
    "true", "false")

  lexical.delimiters += (",", "{", "}", "<", ">", "=")

  def field: Parser[Field[_]] = ("<" ~> fieldParams <~ ">") ^^ {field =>
    Field(
      name = field("name").asInstanceOf[FieldName],
      caption = field("caption").asInstanceOf[I18nString],
      `type` = field("type").asInstanceOf[FieldType[_]],
      visible = field.get("visible").map(_.asInstanceOf[DslFormExpr[Boolean]]).getOrElse(true),
      required = field.get("required").exists(_.asInstanceOf[Boolean]),
      store = field.get("store").exists(_.asInstanceOf[Boolean])
    )
  }

  protected[dsl] def fieldParams: Parser[Map[String, Any]] = rep1sep(fieldParam, ",") ^^ {_.toMap}

  protected[dsl] def fieldParam: Parser[(String, Any)] =
    fieldName | fieldCaption | (fieldType ^^ {"type" -> _}) | fieldDescription | fieldPlaceholder | fieldVisible |
      fieldRequired | store


  protected[dsl] def fieldName: Parser[(String, FieldName)] = "name" ~> "=" ~> stringLit ^^ {"name" -> _}

  protected[dsl] def fieldCaption: Parser[(String, I18nString)] = "caption" ~> "=" ~> i18nString ^^ {"caption" -> _}

  protected[dsl] def fieldDescription: Parser[(String, I18nString)] = "description" ~> "=" ~> i18nString ^^ {"description" -> _}

  protected[dsl] def fieldPlaceholder: Parser[(String, I18nString)] = "placeholder" ~> "=" ~> i18nString ^^ {"placeholder" -> _}

  protected[dsl] def fieldVisible: Parser[(String, DslFormExpr[Boolean])] = "visible" ~> "=" ~> formExpr ^^ {"visible" -> _.asInstanceOf[DslFormExpr[Boolean]]}

  protected[dsl] def fieldRequired: Parser[(String, Boolean)] = "required" ~> "=" ~> booleanLit ^^ {"required" -> _}

  protected[dsl] def store: Parser[(String, Boolean)] = "store" ~> "=" ~> booleanLit ^^ {"store" -> _}

}
