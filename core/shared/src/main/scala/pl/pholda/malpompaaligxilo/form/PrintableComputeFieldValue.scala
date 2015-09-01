package pl.pholda.malpompaaligxilo.form

import pl.pholda.malpompaaligxilo.i18n.{Lang, TranslationProvider}

trait PrintableComputeFieldValue {
  def str(implicit lang: Lang, translationProvider: TranslationProvider): String
}
