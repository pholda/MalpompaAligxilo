package pl.pej.malpompaaligxilo.examples.i18n

import pl.pej.malpompaaligxilo.form.field._
import pl.pej.malpompaaligxilo.form.{Context, Field, Form}
import pl.pej.malpompaaligxilo.util.{PoCfg, I18n, NoI18nString}

class I18nForm(rawFieldValue: Field[_] => Seq[String], val isFilled: Boolean = false)(implicit val context: Context, poCfg: PoCfg) extends Form {
  override val id: String = "i18n"

  override protected def getRawFieldValue(field: Field[_]): Seq[String] = rawFieldValue(field)

  override def fields: List[Field[_]] = name :: surname :: birthDate :: age :: Nil

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