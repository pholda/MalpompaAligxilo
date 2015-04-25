package pl.pej.malpompaaligxilo.form

import pl.pej.malpompaaligxilo.util._

trait PrintableCalculateFieldValue {
  def str(implicit lang: Lang, poCfg: PoCfg): String
}
