package pl.pholda.malpompaaligxilo.form

import pl.pholda.malpompaaligxilo.i18n.{Lang, PoCfg}

trait PrintableCalculateFieldValue {
  def str(implicit lang: Lang, poCfg: PoCfg): String
}
