package pl.pej.malpompaaligxilo.examples.simple

import pl.pej.malpompaaligxilo.form.errors.OtherError
import pl.pej.malpompaaligxilo.form.field._
import pl.pej.malpompaaligxilo.form.{FieldName, Field, Form}
import pl.pej.malpompaaligxilo.util.{NoI18nString, Dates}

import scala.scalajs.js.annotation.{JSExport, JSExportAll}


//@JSExportAll
class SimpleForm(rawFieldValue: FieldName => Seq[String])(implicit val dates: Dates) extends Form {
  override val id: String = "simpleForm"

  override protected def getRawFieldValue(fieldName: FieldName): Seq[String] = rawFieldValue(fieldName)

  override def fields: List[Field[_]] = name :: surname :: birthDate :: country :: age :: Nil

  val name = Field(
    name = "name",
    caption = NoI18nString("Name"),
    `type` = StringField(),
    required = true
  )

  val surname = Field(
    name = "surname",
    caption = NoI18nString("Surname"),
    `type` = StringField(),
    required = true
  )

  val birthDate = Field(
    name = "birthDate",
    caption = NoI18nString("Date of birth"),
    `type` = DateField(),
    required = true
  )

  val country = Field(
    name = "country",
    caption = NoI18nString("Country"),
    `type` = SelectField(List(
      EnumOption("a", NoI18nString("aaaa")),
      EnumOption("b", NoI18nString("bbbb"))
    )),
    required = true
  )

  val age = Field(
    name = "age",
    caption = NoI18nString("Age"),
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