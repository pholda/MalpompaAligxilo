package pl.pej.malpompaaligxilo.form

import pl.pej.malpompaaligxilo.util.NoI18nString

/**
 * Created by piotr on 16.02.15.
 */
package object field {
  implicit def string2noI18nString(str: String): NoI18nString = NoI18nString(str)
}
