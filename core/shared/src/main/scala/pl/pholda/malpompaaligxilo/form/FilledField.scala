package pl.pholda.malpompaaligxilo.form


case class FilledField[T](field: Field[T], value: Option[T], error: Option[FormError])

object FilledField {
  implicit def filledField2field[T](filled: FilledField[T]): Field[T] = filled.field
}