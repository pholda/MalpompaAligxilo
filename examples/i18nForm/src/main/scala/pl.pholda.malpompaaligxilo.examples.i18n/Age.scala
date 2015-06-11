package pl.pholda.malpompaaligxilo.examples.i18n

import pl.pholda.malpompaaligxilo.form.PrintableCalculateFieldValue
import pl.pholda.malpompaaligxilo.i18n.{Lang, I18n, PoCfg}

case class Age(age: Int) extends PrintableCalculateFieldValue {
//  override def toString: String = str("eo")

  def str(implicit lang: Lang, poCfg: PoCfg): String = s"$age ${I18n.t("years")}"
}
