package pl.pholda.malpompaaligxilo.angular

import biz.enef.angulate.Scope
import scala.scalajs.js

trait FormScope extends Scope {

  var field: js.Dictionary[Any] = js.native

  var fieldVisible: js.Function = js.native

  var calculateValue: js.Function = js.native
}
