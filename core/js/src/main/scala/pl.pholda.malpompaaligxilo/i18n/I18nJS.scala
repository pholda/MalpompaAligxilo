package pl.pholda.malpompaaligxilo.i18n

import scalajs.js
import scalajs.js._

class I18nJS(obj: js.Dynamic) extends I18n {
  override def t(singular: String): I18nableString =
    NoI18nString(obj.selectDynamic(singular).asInstanceOf[String])

  override def tp(singular: String, plural: String): I18nableString =
    NoI18nString("not implemented")

  override def tc(ctx: String, singular: String): I18nableString =
    NoI18nString("not implemented")

  override def tcp(ctx: String, singular: String, plural: String): I18nableString =
    NoI18nString("not implemented")
}
