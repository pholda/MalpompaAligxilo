package pl.pholda.malpompaaligxilo.form.db

import pl.pholda.malpompaaligxilo.form.Field
import pl.pholda.malpompaaligxilo.form.db.DbReader.Criterion

import scala.concurrent.Future

case class DbReaderJS(url: String) extends DbReader {
  override final def readAll(fields: Field[Any]*)(criteria: Criterion[Any]*): Future[Map[Field[Any], Any]] =
    Future.failed(new UnsupportedOperationException)

  override def isUniqueValue[T](field: Field[T], value: T): Future[Boolean] = {
//    jQuery
    ???
  }
}
