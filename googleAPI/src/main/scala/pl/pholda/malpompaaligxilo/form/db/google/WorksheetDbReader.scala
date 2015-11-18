package pl.pholda.malpompaaligxilo.form.db.google

import pl.pholda.malpompaaligxilo.form.Field
import pl.pholda.malpompaaligxilo.form.db.DbReader
import pl.pholda.malpompaaligxilo.form.db.DbReader.Criterion
import pl.pholda.malpompaaligxilo.googleapi.Worksheet

import scala.concurrent.{ExecutionContext, Future}

class WorksheetDbReader(worksheet: Worksheet)(implicit executionContext: ExecutionContext) extends DbReader {
  override def readAll(fields: Field[Any]*)(criteria: Criterion[Any]*): Future[Map[Field[Any], Any]] = {
    ???
  }

  override def isUniqueValue[T](field: Field[T], value: T): Future[Boolean] = {
    val separatedValues = field.separatedValues(Some(value))
    worksheet.existsRow(separatedValues).map(!_)
  }
}
