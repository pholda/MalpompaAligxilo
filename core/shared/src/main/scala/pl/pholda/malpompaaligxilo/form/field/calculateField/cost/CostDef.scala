package pl.pholda.malpompaaligxilo.form.field.calculateField.cost

import pl.pholda.malpompaaligxilo.form.{FormInstance, FormExpr}
import pl.pholda.malpompaaligxilo.form.field.calculateField.cost.CostValue.{ComplexCostValue, MultipleCostValue, SingleCostValue}
import pl.pholda.malpompaaligxilo.i18n._

abstract class CostDef {
  def value(implicit formInstance: FormInstance[_]): CostValue
}

object CostDef {

  case class SingleCostDef(
    item: String,
    description: I18nString,
    cost: Double,
    active: FormExpr[Boolean]) extends CostDef {

    override def value(implicit formInstance: FormInstance[_]): CostValue =
      SingleCostValue(item, description, if (active(formInstance)) cost else 0d)
  }

  case class MultipleCostDef(
    item: String,
    description: I18nString,
    itemCost: Double,
    quantity: FormExpr[Int]) extends CostDef {

    override def value(implicit formInstance: FormInstance[_]): CostValue =
      MultipleCostValue(item, description, itemCost, quantity(formInstance))
  }

  case class ComplexCostDef(items: List[CostDef]) extends CostDef {
    override def value(implicit formInstance: FormInstance[_]): CostValue =
      ComplexCostValue(items.map(_.value))
  }
}
