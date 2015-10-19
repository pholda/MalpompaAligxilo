package pl.pholda.malpompaaligxilo.examples.i18n

import pl.pholda.malpompaaligxilo.Context
import pl.pholda.malpompaaligxilo.form.field._
import pl.pholda.malpompaaligxilo.form.field.calculateField.cost.CostDef.SingleCostDef
import pl.pholda.malpompaaligxilo.form.field.calculateField.cost.CostValue.{ComplexCostValue, MultipleCostValue, SingleCostValue}
import pl.pholda.malpompaaligxilo.form.field.calculateField.cost.{CostValue, CostsField}
import pl.pholda.malpompaaligxilo.form.{FormExpr, FormInstance, Field, FormSpecification}
import pl.pholda.malpompaaligxilo.i18n.I18nString
import pl.pholda.malpompaaligxilo.templates.html.costValue

class I18nFormSpec(implicit context: Context) extends FormSpecification {
  import context.translationProvider

  override def fields: List[Field[_]] = name :: surname :: hasMiddleName :: middleName :: birthDate :: age ::
    cost :: Nil

  override def id: String = "i18nForm"

  val name = Field(
    name = "name",
    caption = translationProvider.t("Name"),
    `type` = StringField(),
    required = true
  )

  val surname = Field(
    name = "surname",
    caption = translationProvider.t("Surname"),
    `type` = StringField(),
    required = true
  )

  val hasMiddleName = Field(
    name = "hasMiddleName",
    caption = translationProvider.t("Has middle name"),
    `type` = CheckboxField(default = false)
  )

  val middleName = Field(
    name = "middleName",
    caption = translationProvider.t("Middle name"),
    `type` = StringField(),
    visible = FormExpr{implicit form =>
      hasMiddleName.value.contains(true)
    }
  )

  val birthDate = Field(
    name = "birthDate",
    caption = translationProvider.t("Date of birth"),
    `type` = DateField(),
    required = true
  )

  val age = Field(
    name = "age",
    caption = translationProvider.t("Age"),
    `type` = CustomComputeField[Age](FormExpr{implicit form =>
        birthDate.value match {
          case Some(bd) =>
            Some(Age(form.dates.now.getYear - bd.getYear))
          case _ => None
        }

    }),
    visible = FormExpr{ implicit form =>
      birthDate.value.nonEmpty
    }
  )

  val cost = Field(
    name = "Cost",
    caption = "Cost",
    `type` = CostsField(
      definition = SingleCostDef("age", translationProvider.t("discount for young"), -10, FormExpr{implicit f=>age.value.exists(_.age<10)}),
      currencyFormat = "%.2f :-)"
    )(
      "total = "+_.total
    )
  )

  def costPrinter(costValue: CostValue): I18nString = costValue match {
    case SingleCostValue(_, desc, value) =>
      desc + ": " + value.toString
    case multiple@MultipleCostValue(_, desc, itemCost, items) =>
      desc + ": " + (multiple.total + " ("+itemCost+" × "+items+")")
    case ComplexCostValue(values) =>
      ""
  }
}