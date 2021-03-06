package pl.pholda.malpompaaligxilo.angular

import biz.enef.angulate.Scope
import pl.pholda.malpompaaligxilo.form.Field
import scala.scalajs.js

trait FormScope extends Scope {

  var fieldValue: js.Dictionary[Any] = js.native

  var fields: js.Dictionary[Field[_]] = js.native

  var fieldVisible: js.Function = js.native

  var computedValue: js.Function = js.native

  var dateOptions: js.Dictionary[Any] = js.native

  var submit: js.Function = js.native

  var submitted: Boolean = js.native

  var minDate: js.Function = js.native
}
