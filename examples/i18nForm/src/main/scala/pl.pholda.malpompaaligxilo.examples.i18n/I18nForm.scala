package pl.pholda.malpompaaligxilo.examples.i18n

import pl.pholda.malpompaaligxilo.Context
import pl.pholda.malpompaaligxilo.form.field._
import pl.pholda.malpompaaligxilo.form.{Field, Form}
import pl.pholda.malpompaaligxilo.i18n.{I18n, PoCfg}

class I18nForm(rawFieldValue: Field[_] => Seq[String], val isFilled: Boolean = false)(implicit val context: Context, poCfg: PoCfg) extends Form {
  override val id: String = "i18n"

  override protected def getRawFieldValue(field: Field[_]): Seq[String] = rawFieldValue(field)

  override def fields: List[Field[_]] = name :: surname :: hasMiddleName :: middleName :: birthDate :: age :: Nil

  val name = Field(
    name = "name",
    caption = I18n.po("Name"),
    `type` = StringField(),
    required = true
  )

  val surname = Field(
    name = "surname",
    caption = I18n.po("Surname"),
    `type` = StringField(),
    required = true
  )

  val hasMiddleName = Field(
    name = "hasMiddleName",
    caption = I18n.po("Has middle name"),
    `type` = CheckboxField(default = false)
  )

  val middleName = Field(
    name = "middleName",
    caption = I18n.po("Middle name"),
    `type` = StringField(),
    visible = { implicit form =>
      hasMiddleName.value.contains(true)
    }
  )

  val birthDate = Field(
    name = "birthDate",
    caption = I18n.po("Date of birth"),
    `type` = DateField(),
    required = true
  )

  val age = Field(
    name = "age",
    caption = "Age",
    `type` = CustomCalculateField[Age]{ implicit form =>
      birthDate.value match {
        case Some(bd) =>
          Some(Age(dates.now.getYear - bd.getYear))
        case _ => None
      }
    },
    visible = { implicit form =>
      birthDate.value.nonEmpty
    }
  )
}