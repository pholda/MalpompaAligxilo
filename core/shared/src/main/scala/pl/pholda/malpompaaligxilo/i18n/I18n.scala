package pl.pholda.malpompaaligxilo.i18n

import pl.pholda.malpompaaligxilo.util._

import scala.io.Source

abstract class I18n {

  def t(singular: String): I18nableString

  def tc(ctx: String, singular: String): I18nableString

  def tp(singular: String, plural: String): I18nableString

  def tcp(ctx: String, singular: String, plural: String): I18nableString
}

object I18n {
  def t(singular: String)(implicit i18n: I18n, lang: Lang): String =
    i18n.t(singular)(lang)

  def tc(ctx: String, singular: String)(implicit i18n: I18n, lang: Lang): String =
    i18n.tc(ctx, singular)(lang)

  def tp(singular: String, plural: String)(implicit i18n: I18n, lang: Lang): String =
    i18n.tp(singular, plural)(lang)

  def tcp(ctx: String, singular: String, plural: String)(implicit i18n: I18n, lang: Lang): String =
    i18n.tcp(ctx, singular, plural)(lang)
}
