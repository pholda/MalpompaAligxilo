package pl.pholda.malpompaaligxilo.form

abstract class FormElement {
  def name: FieldName
  def visible: FormExpr[Boolean]
}
