package pl.pholda.malpompaaligxilo.form.field

import pl.pholda.malpompaaligxilo.form.errors.NothingSelectedError
import pl.pholda.malpompaaligxilo.form.{FieldType, FormError}

case class SelectField(
  options: List[EnumOption],
  size: Int = 1,
  notSelected: Option[EnumOption] = None,
  ordering: EnumOptionOrdering = EnumOptionOrdering.byValue
                        ) extends FieldType[EnumOption] {

  lazy val allOptions = notSelected match {
    case Some(o) => o :: options
    case None => options
  }

  override def parse(values: Seq[String]): Option[EnumOption] =
    values.headOption.flatMap(v => allOptions.find(_.value == v)).orElse(notSelected)

  override def validate(t: EnumOption): Option[FormError] = {
    notSelected match {
      case Some(ns) if ns.value == t.value => Some(NothingSelectedError)
      case _ => None
    }
  }

  override val arrayValue: Boolean = false
}
