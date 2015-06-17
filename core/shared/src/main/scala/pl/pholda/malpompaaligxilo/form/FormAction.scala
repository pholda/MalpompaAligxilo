package pl.pholda.malpompaaligxilo.form

abstract class FormAction {
  def run(form: FormInstance): Unit
}
