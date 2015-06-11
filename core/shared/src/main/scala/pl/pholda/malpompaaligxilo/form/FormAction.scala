package pl.pholda.malpompaaligxilo.form

abstract class FormAction[F <: Form] {
  def run(form: F): Unit
}
