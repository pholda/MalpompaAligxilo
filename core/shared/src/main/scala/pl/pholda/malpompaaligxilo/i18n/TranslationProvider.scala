package pl.pholda.malpompaaligxilo.i18n

abstract class TranslationProvider {

  def t(singular: String): I18nableString

  def tc(ctx: String, singular: String): I18nableString

  def tp(singular: String, plural: String): I18nableString

  def tcp(ctx: String, singular: String, plural: String): I18nableString

}


