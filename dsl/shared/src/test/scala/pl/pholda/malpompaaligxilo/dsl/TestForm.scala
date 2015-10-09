package pl.pholda.malpompaaligxilo.dsl

import pl.pholda.malpompaaligxilo.Context
import pl.pholda.malpompaaligxilo.form.field._
import pl.pholda.malpompaaligxilo.form.{FormInstance, Field, FormSpecification}
import pl.pholda.malpompaaligxilo.i18n.{TranslationProvider, NoI18nString}

trait TestForm {
  implicit val translationProvider: TranslationProvider

  implicit val context: Context

  class Form extends FormSpecification {
    override def fields: List[Field[_]] = a :: date :: intField :: selectField :: cbTable :: Nil

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

    override def id: String = "id"
  }

  val form: FormInstance[_]
}
