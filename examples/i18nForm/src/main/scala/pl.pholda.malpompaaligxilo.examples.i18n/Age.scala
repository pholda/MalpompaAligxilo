package pl.pholda.malpompaaligxilo.examples.i18n

import pl.pholda.malpompaaligxilo.form.PrintableCalculateFieldValue
import pl.pholda.malpompaaligxilo.i18n.{I18n, Lang}

case class Age(age: Int) extends PrintableCalculateFieldValue {

  def str(implicit lang: Lang, i18n: I18n): String = s"$age ${I18n.t("years")}"
}