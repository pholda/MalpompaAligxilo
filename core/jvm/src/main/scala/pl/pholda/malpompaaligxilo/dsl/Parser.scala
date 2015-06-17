//package pl.pholda.malpompaaligxilo.dsl
//
//import pl.pholda.malpompaaligxilo.form.field.StringField
//import pl.pholda.malpompaaligxilo.form.{Field, FieldName, FieldType}
//import pl.pholda.malpompaaligxilo.i18n.{NoI18nString, I18nString}
//import pl.pholda.malpompaaligxilo.util.I18nableString
//
//import scala.util.parsing.combinator.syntactical.StandardTokenParsers
//
//case class Params()
//
//object Parser extends StandardTokenParsers {
//
//  lexical.reserved += ("form", "fields", "name", "store", "caption", "type", "description", "placeholder",
//    "visible", "required")
//
//  lexical.delimiters += (",", "{", "}", "<", ">", "=")
//
//  lazy val fieldTypes: Map[String, Params => FieldType[_]] = Map(
//    "string" -> {
//      _ => StringField()
//    }
//  )
//
//  def i18nString = rep1sep(
//      (stringLit ~ "=" ~ stringLit) ^^ {
//        case lang ~ _ ~ value => lang -> value
//      },
//      ","
//  ) ^^ {
//    case list => I18nString(list:_*)
//  }
//
//  def no18nString = stringLit ^^ {s => NoI18nString(s)}
//
//  def i18nableString = i18nString | no18nString
//
//  def boolean = ("true" ^^^ true) | ("false" ^^^ false)
//
//  def form = "form" ~> "{" ~> fields <~ "}" ^^ {
//    case fields => DslForm(fields)
//  }
//
//  def fields = "fields" ~> "=" ~> "{" ~> rep1sep(field, ",")
//
//  def fieldParamName = "name" ~> "=" ~> stringLit ^^ {
//    "name" -> _
//  }
//  def fieldParamCaption = "caption" ~> "=" ~> i18nableString ^^ {
//    "caption" -> _
//  }
//  def fieldParamType = "type" ~> "=" ~> stringLit ^^ {
//    "type" -> _
//  }
//
//  def fieldDescription = "description" ~> "=" ~> i18nableString ^^ {
//    "description" -> _
//  }
//  def fieldPlaceholder = "placeholder" ~> "=" ~> i18nableString ^^ {
//    "placeholder" -> _
//  }
//  def fieldVisible = "visible" ~> "=" ~> boolean ^^ {
//    "visible" -> _
//  }
//  def fieldRequired = "required" ~> "=" ~> boolean ^^ {
//    "required" -> _
//  }
//  def fieldCustomValidate = ???
//  def fieldStore = "store" ~> "=" ~> boolean ^^ {
//    "store" -> _
//  }
//
//  def fieldParam: Parser[(String, Any)] = fieldParamName | fieldParamCaption | fieldParamType | fieldDescription | fieldPlaceholder |
//    fieldVisible | fieldRequired /*| fieldCustomValidate */| fieldStore
//
//  def fieldParams = rep1sep(fieldParam, ",") ^^ {_.toMap}
//
//  def field = /*"field" ~> */"<" ~> fieldParams <~ ">" ^^ {params =>
//      Field(
//        name = params("name").asInstanceOf[FieldName],
//        caption = params("caption").asInstanceOf[I18nableString],
//        `type` = StringField(),
//        description = params.get("description").map(_.asInstanceOf[I18nableString]),
//        placeholder = params.get("placeholder").map(_.asInstanceOf[I18nableString]),
//        required = params.getOrElse("required", false).asInstanceOf[Boolean],
//        store = params.getOrElse("store", true).asInstanceOf[Boolean]
//      )
//  }
//
//  def apply(str: String) = {
//    form.apply(new lexical.Scanner(str)) match {
//      case Success(result, _) => result
//      case Error(msg, _) => throw new Exception(msg)
//      case Failure(msg, _) => throw new Exception(msg)
//    }
//  }
//}
