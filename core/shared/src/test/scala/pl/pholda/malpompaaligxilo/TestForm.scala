package pl.pholda.malpompaaligxilo

import pl.pholda.malpompaaligxilo.form.field._
import pl.pholda.malpompaaligxilo.form.field.calculateField.cost.CostDef.{MultipleCostDef, SingleCostDef}
import pl.pholda.malpompaaligxilo.form.field.calculateField.cost.CostValue.MultipleCostValue
import pl.pholda.malpompaaligxilo.form.field.calculateField.cost.CostsField
import pl.pholda.malpompaaligxilo.form.{FormExpr, Field, FormInstance, FormSpecification}
import pl.pholda.malpompaaligxilo.i18n.{NoI18nString, TranslationProvider}

trait TestForm {
  implicit val translationProvider: TranslationProvider

  implicit val context: Context

  object TestFormSpec extends FormSpecification {
    override def fields: List[Field[_]] = a :: date :: intField :: selectField :: cbTable ::
      singleCost :: multipleCost :: Nil

    val a = Field(
      name = "a",
      caption = NoI18nString("a"),
      `type` = StringField()
    )

    val date = Field(
      name = "date",
      caption = NoI18nString("date"),
      `type` = DateField()
    )

    val intField = Field(
      name = "intField",
      caption = NoI18nString("int field"),
      `type` = IntField()
    )

    val selectField = Field(
      name = "selectField",
      caption = NoI18nString("Select Field"),
      `type` = SelectField(List(
        EnumOption("1", NoI18nString("first")),
        EnumOption("2", NoI18nString("second"))
      ))
    )

    val cbTable = Field(
      name = "cbt",
      caption = NoI18nString("Checkbox table field"),
      `type` = CheckboxTableField(
        List(
          CheckboxTableRow("a", NoI18nString("a")),
          CheckboxTableRow("b", NoI18nString("b"))
        ), List(
          CheckboxTableCol("1", NoI18nString("1")),
          CheckboxTableCol("2", NoI18nString("2"))
        ))
    )

    val singleCost = Field(
      name = "singleCost",
      caption = NoI18nString("singleCost"),
      `type` = CostsField(
        definition = SingleCostDef("a", NoI18nString("a"), 50, FormExpr{f =>
          a.value(f).contains("value-a")
        }),
        "total = "+_.total
      )
    )

    val multipleCost = Field(
      name = "multipleCost",
      caption = NoI18nString("multipleCost"),
      `type` = CostsField(
        definition = MultipleCostDef("b", NoI18nString("b"), 10, FormExpr{ f =>
          intField.value(f).get
        }),
        "total = "+_.total
      )
    )

    override def id: String = "id"
  }

  implicit val form: FormInstance[_]
}
