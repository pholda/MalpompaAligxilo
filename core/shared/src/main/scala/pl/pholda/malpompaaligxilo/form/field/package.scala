package pl.pholda.malpompaaligxilo.form

import pl.pholda.malpompaaligxilo.i18n.NoI18nString


package object field {
  implicit def string2noI18nString(str: String): NoI18nString = NoI18nString(str)


  @deprecated
  type TableCheckboxField = CheckboxTableField
  @deprecated
  type TableCheckboxRow = CheckboxTableRow
  @deprecated
  type TableCheckboxCol = CheckboxTableCol
}
