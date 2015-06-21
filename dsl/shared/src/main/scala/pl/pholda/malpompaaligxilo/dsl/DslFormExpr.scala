package pl.pholda.malpompaaligxilo.dsl

import pl.pholda.malpompaaligxilo.form.{FormExpr, FormInstance}
import pl.pholda.malpompaaligxilo.i18n.{I18nableString, NoI18nString}

sealed abstract class DslFormExpr[+T] extends FormExpr[T]

object DslFormExpr {
  case class Compare(a: DslFormExpr[_], b: DslFormExpr[_]) extends DslFormExpr[Boolean] {
    override def apply(form: FormInstance): Boolean = a(form) == b(form)
  }

  case class Literal[T](value: T) extends DslFormExpr[T] {
    override def apply(form: FormInstance): T = value
  }

  case class StringConcat(a: DslFormExpr[_]*) extends DslFormExpr[Any] {
    override def apply(formInstance: FormInstance): Any = a map {expr =>
      expr(formInstance) match {
        case string: String =>
          NoI18nString(string)
        case i18nable: I18nableString =>
          i18nable
        case x =>
          NoI18nString(s"<$x>")
      }
    } reduce(_ + _)

  }

  case class FieldValueOpt(fieldName: String) extends DslFormExpr[Option[Any]] {
    override def apply(form: FormInstance): Option[Any] = form.fieldsByName(fieldName).value(form)
  }

  case class FieldValue(fieldName: String) extends DslFormExpr[Any] {
    override def apply(form: FormInstance): Any = form.fieldsByName(fieldName).value(form).get
  }

}
