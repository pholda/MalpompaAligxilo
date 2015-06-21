package pl.pholda.malpompaaligxilo.dsl.parser

import pl.pholda.malpompaaligxilo.dsl._
import pl.pholda.malpompaaligxilo.form.{Field, FieldName, FieldType}
import pl.pholda.malpompaaligxilo.i18n.{I18nString, I18n}

import scala.util.parsing.combinator.syntactical.StandardTokenParsers

case class FormSpecificationParser(implicit i18n: I18n) extends FormSpecificationParserTrait

trait FormSpecificationParserTrait extends StandardTokenParsers
  with FormExprParser
  with FieldTypeParser
  with UtilParsers
  with I18nParser  {

   lexical.reserved += ("form", "fields", "name", "store", "caption",
    "description", "placeholder",
    "visible", "required",
    "true", "false")

  lexical.delimiters += (",", "{", "}", "<", ">", "=", "@")

  //fix for scalajs :(
  lexical.reserved foreach {
    lexical.delimiters += _
  }

  def fields: Parser[List[Field[_]]] = "fields" ~> "=" ~> "{" ~> rep1sep(field, opt(",")) <~ "}"

  protected[dsl] def field: Parser[Field[_]] = ("<" ~> fieldParams <~ ">") ^^ {field =>
    Field(
      name = field("name").asInstanceOf[FieldName],
      caption = field("caption").asInstanceOf[I18nString],
      `type` = field("type").asInstanceOf[FieldType[_]],
      visible = field.get("visible").map(_.asInstanceOf[DslFormExpr[Boolean]]).getOrElse(true)
    )
  }

  protected[dsl] def fieldParams: Parser[Map[String, Any]] = rep1sep(fieldParam, ",") ^^ {_.toMap}

  protected[dsl] def fieldParam: Parser[(String, Any)] = fieldName | fieldCaption | fieldType | fieldVisible//..

  protected[dsl] def fieldName: Parser[(String, FieldName)] = "name" ~> "=" ~> stringLit ^^ {"name" -> _}

  protected[dsl] def fieldCaption: Parser[(String, I18nString)] = "caption" ~> "=" ~> i18nString ^^ {"caption" -> _}

  protected[dsl] def fieldDescription: Parser[(String, I18nString)] = "description" ~> "=" ~> i18nString ^^ {"description" -> _}

  protected[dsl] def fieldPlaceholder: Parser[(String, I18nString)] = "placeholder" ~> "=" ~> i18nString ^^ {"placeholder" -> _}

  protected[dsl] def fieldVisible: Parser[(String, DslFormExpr[Boolean])] = "visible" ~> "=" ~> formExpr ^^ {"visible" -> _.asInstanceOf[DslFormExpr[Boolean]]}

  protected[dsl] def fieldRequired: Parser[(String, Boolean)] = "required" ~> "=" ~> booleanLit ^^ {"required" -> _}

  protected[dsl] def store: Parser[(String, Boolean)] = "store" ~> "=" ~> booleanLit ^^ {"store" -> _}

  def apply(input: String): Option[DslFormSpecification] = {
    fields.apply(new lexical.Scanner(input)) match {
      case Success(fields, _) => Some(new DslFormSpecification(fields, "noid"))
      case Failure(msg, next) =>
        None
      case _ => None
    }
  }
}
