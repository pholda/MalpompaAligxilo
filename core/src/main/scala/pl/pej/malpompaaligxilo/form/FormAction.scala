package pl.pej.malpompaaligxilo.form

abstract class FormAction[F <: Form] {
  def run(form: F): Unit
}
