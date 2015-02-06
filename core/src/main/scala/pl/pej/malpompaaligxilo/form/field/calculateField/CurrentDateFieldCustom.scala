//package pl.pej.malpompaaligxilo.form.field.calculateField
//
//import pl.pej.malpompaaligxilo.form.FormExpr
//import pl.pej.malpompaaligxilo.form.field.CalculateField
//import pl.pej.malpompaaligxilo.util.{Dates, Date}
//
//import scala.util.Try
//
//case class CurrentDateFieldCustom()(implicit dates: Dates) extends CalculateField[Date] {
//  override def formula: FormExpr[Option[Date]] = Some(dates.now)
//}
