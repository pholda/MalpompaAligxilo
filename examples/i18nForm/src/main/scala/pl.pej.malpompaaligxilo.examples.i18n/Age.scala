package pl.pej.malpompaaligxilo.examples.i18n

import pl.pej.malpompaaligxilo.form.PrintableCalculateFieldValue
import pl.pej.malpompaaligxilo.util.{PoCfg, Lang, I18n}

case class Age(age: Int) extends PrintableCalculateFieldValue {
//  override def toString: String = str("eo")

  def str(implicit lang: Lang, poCfg: PoCfg): String = s"$age ${I18n.t("years")}"
}
