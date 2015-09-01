package pl.pholda.malpompaaligxilo.examples.i18n

import pl.pholda.malpompaaligxilo.Context
import pl.pholda.malpompaaligxilo.form.field._
import pl.pholda.malpompaaligxilo.form.{FormExpr, FormInstance, Field, FormSpecification}

class I18nFormSpec(implicit context: Context) extends FormSpecification {
  import context.translationProvider

  override def fields: List[Field[_]] = name :: surname :: hasMiddleName :: middleName :: birthDate :: age :: Nil

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
    caption = "Age",
    `type` = CustomCalculateField[Age](FormExpr{implicit form =>
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
}