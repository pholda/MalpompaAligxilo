//package pl.pej.malpompaaligxilo.form
//
//case class ParsedForm(form: Form, data: Seq[FilledField[_]]) {
//
//  lazy val fields: Map[Field[_], FilledField[_]] = data.map(f => f.field -> f).toMap
//
//  lazy val errors: Map[Field[_], FormError] = form.fields.map{f =>
//    f
//  }
//
//  //data.map(f => f.error.map(e => f.field -> e)).flatten.toMap
//
//  lazy val validate: ValidationResult = {
//    if (errors.isEmpty) {
//      SuccessValidation
//    } else {
//      FailureValidation(errors)
//    }
//  }
//}
