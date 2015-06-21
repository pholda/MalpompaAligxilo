package pl.pholda.malpompaaligxilo.dsl

import pl.pholda.malpompaaligxilo.form.{Field, FormSpecification}

case class DslFormSpecification(fields: List[Field[_]], id: String) extends FormSpecification
