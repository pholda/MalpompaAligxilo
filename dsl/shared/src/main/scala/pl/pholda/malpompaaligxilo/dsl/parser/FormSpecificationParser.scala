package pl.pholda.malpompaaligxilo.dsl.parser

import pl.pholda.malpompaaligxilo.Context
import pl.pholda.malpompaaligxilo.dsl._
import pl.pholda.malpompaaligxilo.dsl.form.DslFormSpecification
import pl.pholda.malpompaaligxilo.dsl.parser.expr.FormExprParser
import pl.pholda.malpompaaligxilo.form.{Field, FieldName, FieldType}
import pl.pholda.malpompaaligxilo.i18n.{TranslationProvider, I18nString, TranslationProvider$}
import pl.pholda.malpompaaligxilo.util.DateCompanion

import scala.util.parsing.combinator.PackratParsers
import scala.util.parsing.combinator.syntactical.StandardTokenParsers
import scala.util.parsing.input.CharSequenceReader

case class FormSpecificationParser(implicit context: Context) extends FormSpecificationParserTrait {
  override protected implicit val dateCompanion: DateCompanion = context.date
  override implicit val translationProvider: TranslationProvider = context.translationProvider
}

trait FormSpecificationParserTrait extends StandardTokenParsers
  with FieldParser with PackratParsers {

  lexical.reserved += ("fields")

  lexical.delimiters += ("=", "{", "}", ",")

  //fix for scalajs :(
  lexical.reserved foreach {
    lexical.delimiters += _
  }

  def fields: PackratParser[List[Field[_]]] = "fields" ~> "=" ~> "{" ~> rep1sep(field, opt(",")) <~ "}"

//  def apply(input: String): Option[DslFormSpecification] = {
//    fields.apply(new lexical.Scanner(input)) match {
//      case Success(fields, _) => Some(new DslFormSpecification(fields, "noid"))
//      case Failure(msg, next) =>
//        throw new Exception(msg)
//      case _ => None
//    }
//  }

  def apply(input: String): Option[DslFormSpecification] = {
    phrase(fields)(new PackratReader(new lexical.Scanner(input)))match {
      case Success(fields, _) => Some(new DslFormSpecification(fields, "noid"))
      case Failure(msg, next) =>
        throw new Exception(msg)
      case _ => None
    }
//    fields.apply(new PackratReader(new CharSequenceReader(input)))
//    val r = parseAll(phrase(fields), new PackratReader(new CharSequenceReader(input)))
  }

}
