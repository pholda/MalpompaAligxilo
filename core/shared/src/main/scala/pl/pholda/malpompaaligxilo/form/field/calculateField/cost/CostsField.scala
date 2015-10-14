package pl.pholda.malpompaaligxilo.form.field.calculateField.cost

import pl.pholda.malpompaaligxilo.form.FormExpr
import pl.pholda.malpompaaligxilo.form.field.ComputeField
import pl.pholda.malpompaaligxilo.form.field.calculateField.cost.CostValue.{ComplexCostValue, MultipleCostValue, SingleCostValue}
import pl.pholda.malpompaaligxilo.i18n.{I18nableString, TranslationProvider, NoI18nString, I18nString}

import scala.util.Try

case class CostsField(
  definition: CostDef,
  currencyFormat: String
    )(
  val printer: CostValue => I18nString = {costValue =>
    //TODO clean it (templates?)
    def print(cost: CostValue): I18nString = cost match {
      case SingleCostValue(_, description, cost) =>
        NoI18nString("<dt>")+description+NoI18nString("</dt><dd>"+cost.formatted(currencyFormat)+"</dd>")
      case mc@MultipleCostValue(_, description, itemCost, items) =>
        NoI18nString("<dt>")+description+NoI18nString(" ("+itemCost.formatted(currencyFormat)+" × "+items.toString+")</dt><dd>"+mc.total.formatted(currencyFormat)+"</dd>")
      case ComplexCostValue(items) =>
        NoI18nString("<dl>")+items.map(print(_)).reduce(_+_)+NoI18nString("</dl>")
    }

    print(costValue)+NoI18nString("<hr><strong>Σ "+costValue.total.formatted(currencyFormat)+"</strong>")
  }
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