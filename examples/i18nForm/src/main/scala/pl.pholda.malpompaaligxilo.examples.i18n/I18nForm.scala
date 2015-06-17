package pl.pholda.malpompaaligxilo.examples.i18n

import pl.pholda.malpompaaligxilo.Context
import pl.pholda.malpompaaligxilo.form.field._
import pl.pholda.malpompaaligxilo.form.{Field, FormSpecification}

class I18nForm(implicit context: Context) extends FormSpecification {
  import context.i18n

  override def fields: List[Field[_]] = name :: surname :: hasMiddleName :: middleName :: birthDate :: age :: Nil

  override def id: String = "i18nForm"

  val name = Field(
    name = "name",
    caption = i18n.t("Name"),
    `type` = StringField(),
    required = true
  )

  val surname = Field(
    name = "surname",
    caption = i18n.t("Surname"),
    `type` = StringField(),
    required = true
  )

  val hasMiddleName = Field(
    name = "hasMiddleName",
    caption = i18n.t("Has middle name"),
    `type` = CheckboxField(default = false)
  )

  val middleName = Field(
    name = "middleName",
    caption = i18n.t("Middle name"),
    `type` = StringField(),
    visible = { implicit form =>
      hasMiddleName.value.contains(true)
    }
  )

  val birthDate = Field(
    name = "birthDate",
    caption = i18n.t("Date of birth"),
    `type` = DateField(),
    required = true
  )

  val age = Field(
    name = "age",
    caption = "Age",
    `type` = CustomCalculateField[Age]{ implicit form =>
      birthDate.value match {
        case Some(bd) =>
          Some(Age(form.dates.now.getYear - bd.getYear))
        case _ => None
      }
    },
    visible = { implicit form =>
      birthDate.value.nonEmpty
    }
  )
}