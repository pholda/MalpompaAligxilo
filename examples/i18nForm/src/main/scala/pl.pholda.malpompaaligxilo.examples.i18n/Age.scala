package pl.pholda.malpompaaligxilo.examples.i18n

import pl.pholda.malpompaaligxilo.form.PrintableCalculateFieldValue
import pl.pholda.malpompaaligxilo.i18n.{TranslationProvider, I18n, Lang}

case class Age(age: Int) extends PrintableCalculateFieldValue {

  def str(implicit lang: Lang, translationProvider: TranslationProvider): String =
    s"$age ${I18n.t("years")}"
}