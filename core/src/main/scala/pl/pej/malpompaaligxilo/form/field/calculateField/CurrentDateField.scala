package pl.pej.malpompaaligxilo.form.field.calculateField

import pl.pej.malpompaaligxilo.form.FormExpr
import pl.pej.malpompaaligxilo.form.field.CalculateField
import pl.pej.malpompaaligxilo.util.{Dates, Date}

case class CurrentDateField()(implicit dates: Dates) extends CalculateField[Date] {
  override def formula: FormExpr[Option[Date]] = Some(dates.now)
}
