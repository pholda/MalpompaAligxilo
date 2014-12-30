package helper

import pl.pej.malpompaligxilo.form.Field
import play.twirl.api.Html

/**
 * Created by piotr on 29.12.14.
 */
package object malpompaaligxilo {
  val field: Field[_] => Html = {field: Field[_] =>
    views.html.malpompaaligxilo.field(field)
  }
}
