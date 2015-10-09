package pl.pholda.malpompaaligxilo.form.field.calculateField.cost

import pl.pholda.malpompaaligxilo.i18n._

abstract class CostValue {
  def total: Double
}

object CostValue {
  case class SingleCostValue(item: String, description: I18nString, cost: Double) extends CostValue {
    override def total: Double = cost
  }

  case class MultipleCostValue(item: String, description: I18nString, itemCost: Double, items: Int) extends CostValue {
    override lazy val total: Double = itemCost * items
  }

  case class ComplexCostValue(items: List[CostValue]) extends CostValue {
    override lazy val total: Double = items.map(_.total).sum
  }
}
