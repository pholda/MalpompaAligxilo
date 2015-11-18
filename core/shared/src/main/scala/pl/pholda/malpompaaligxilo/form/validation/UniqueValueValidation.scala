package pl.pholda.malpompaaligxilo.form.validation

import pl.pholda.malpompaaligxilo.form.Field
import pl.pholda.malpompaaligxilo.form.db.DbReader
import pl.pholda.malpompaaligxilo.form.field.{SuccessFieldValidation, FailureFieldValidation, FieldValidationResult}
import pl.pholda.malpompaaligxilo.form.validation.errors.CustomError
import pl.pholda.malpompaaligxilo.i18n.NoI18nString

import scala.concurrent.{ExecutionContext, Future}

case class UniqueValueValidation[T](implicit dbReader: DbReader) extends FieldValidation[T] {

  override def validate(field: Field[T], value: T)(implicit executionContext: ExecutionContext): Future[FieldValidationResult] = {
      dbReader.isUniqueValue[T](field, value).map{
        case true => SuccessFieldValidation
        case _ => FailureFieldValidation(CustomError(NoI18nString("aaa")))
      }
  }
}
