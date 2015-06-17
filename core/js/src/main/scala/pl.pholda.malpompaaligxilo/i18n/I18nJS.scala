package pl.pholda.malpompaaligxilo.i18n

import scala.scalajs.js

//TODO: correct other i18n methods, better method of passing formId
class I18nJS(formId: String) extends I18n {

  val i18n: js.Dictionary[String] = js.eval(s"${formId}_i18n").asInstanceOf[js.Dictionary[String]]

  override def t(singular: String): I18nableString =
    NoI18nString(i18n.get(singular).getOrElse("???"))

  override def tp(singular: String, plural: String): I18nableString =
    NoI18nString(i18n.get(singular).getOrElse("???"))

  override def tc(ctx: String, singular: String): I18nableString =
    NoI18nString(i18n.get(singular).getOrElse("???"))

  override def tcp(ctx: String, singular: String, plural: String): I18nableString =
    NoI18nString(i18n.get(singular).getOrElse("???"))
}
