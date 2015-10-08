package pl.pholda.malpompaaligxilo.form.field.calculateField

import pl.pholda.malpompaaligxilo.Context
import pl.pholda.malpompaaligxilo.form.FormExpr
import pl.pholda.malpompaaligxilo.form.field.ComputeField
import pl.pholda.malpompaaligxilo.util.Date

case class CurrentDateField()(implicit context: Context) extends ComputeField[Date] {
  override def formula: FormExpr[Option[Date]] = Some(context.date.now)

  override def separatedValues(value: Option[Date]): List[(String, String)] = ???
}
