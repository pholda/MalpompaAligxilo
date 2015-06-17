package pl.pholda.malpompaaligxilo.form

import pl.pholda.malpompaaligxilo.i18n.{I18n, Lang}

trait PrintableCalculateFieldValue {
  def str(implicit lang: Lang, i18n: I18n): String
}
