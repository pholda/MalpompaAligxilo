package pl.pholda.malpompaaligxilo.i18n

import scala.scalajs.js

class JSObjectTranslationProvider(obj: js.Dynamic) extends TranslationProvider {
  override def t(singular: String): I18nableString = {
    val str = obj.selectDynamic(singular).asInstanceOf[Any] match {
      case s: String =>
        s
      case _ =>
        "?translation not found?"
    }
    NoI18nString(str)
  }


  override def tp(singular: String, plural: String): I18nableString =
    NoI18nString("not implemented")

  override def tc(ctx: String, singular: String): I18nableString =
    NoI18nString("not implemented")

  override def tcp(ctx: String, singular: String, plural: String): I18nableString =
    NoI18nString("not implemented")
}
