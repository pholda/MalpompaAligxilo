package pl.pholda.malpompaaligxilo.form.db

import pl.pholda.malpompaaligxilo.form.Field
import pl.pholda.malpompaaligxilo.form.db.DbReader.Criterion

import scala.concurrent.Future

abstract class DbReader {
  def readAll(fields: Field[Any]*)(criteria: Criterion[Any]*): Future[Map[Field[Any], Any]]

  def isUniqueValue[T](field: Field[T], value: T): Future[Boolean]
}

object DbReader {
  sealed abstract class Criterion[+T] {
    def or[A](criterion: Criterion[A]): Criterion[_] =
      Alternative(this, criterion)

    def and[A](criterion: Criterion[A]): Criterion[_] =
      Conjunction(this, criterion)
  }

  case class Equals[T](field: Field[T], equalsTo: T) extends Criterion[T]

  case class Alternative[T](criteria: Criterion[T]*) extends Criterion[T]

  case class Conjunction[T](criteria: Criterion[T]*) extends Criterion[T]
}
