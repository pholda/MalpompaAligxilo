package pl.pholda.malpompaaligxilo.form.field.calculateField.cost

import pl.pholda.malpompaaligxilo.form.FormExpr
import pl.pholda.malpompaaligxilo.form.field.ComputeField
import pl.pholda.malpompaaligxilo.i18n.I18nString

import scala.util.Try

case class CostsField(
  definition: CostDef,
  printer: CostValue => I18nString
) extends ComputeField[CostValue] {

  override protected def formula: FormExpr[Option[CostValue]] = FormExpr{implicit formInstance => Try{
    definition.value
  }.toOption}

  override def separatedValues(value: Option[CostValue]): List[(String, String)] = value match {
    case Some(costValue) =>
      "" -> costValue.total.toString :: Nil
    case _ =>
      Nil
  }
}

object CostsField {



}