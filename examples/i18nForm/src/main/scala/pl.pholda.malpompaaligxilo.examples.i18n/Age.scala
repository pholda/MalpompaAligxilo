package pl.pholda.malpompaaligxilo.examples.i18n

import pl.pholda.malpompaaligxilo.form.PrintableComputeFieldValue
import pl.pholda.malpompaaligxilo.i18n.{I18n, Lang, TranslationProvider}

case class Age(age: Int) extends PrintableComputeFieldValue {

  def str(implicit lang: Lang, translationProvider: TranslationProvider): String =
    s"$age ${I18n.t("years")}"
}