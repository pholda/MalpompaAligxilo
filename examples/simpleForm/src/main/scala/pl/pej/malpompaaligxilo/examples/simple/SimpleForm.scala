package pl.pej.malpompaaligxilo.examples.simple

import pl.pej.malpompaaligxilo.form.field._
import pl.pej.malpompaaligxilo.form.field.calculateField.FormProgressField
import pl.pej.malpompaaligxilo.form.{Context, Field, FieldName, Form}
import pl.pej.malpompaaligxilo.util.{Dates, NoI18nString}


class SimpleForm(rawFieldValue: Field[_] => Seq[String], val isFilled: Boolean = false)(implicit val context: Context) extends Form {
  override val id: String = "simpleForm"

//  override protected def getRawFieldValue(field: Field[_]): Seq[String] = rawFieldValue(field)


  override protected def getRawFieldValue(field: Field[_]): Seq[String] = rawFieldValue(field)

  override def fields: List[Field[_]] = name :: surname :: birthDate :: country :: age ::
    checkbox :: email :: int :: tableCheckbox :: tableCheckboxSimple :: formProgress :: Nil

  val name = Field(
    name = "name",
    caption = "Name",
    `type` = StringField(),
    required = true
  )

  val surname = Field(
    name = "surname",
    caption = "Surname",
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
    caption = "Country",
    `type` = SelectField(List(
      EnumOption("a", "aaaa"),
      EnumOption("b", "bbbb")
    )),
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

  //other field-types
  val checkbox = Field(
    name = "checkbox",
    caption = "Checkbox",
    `type` = CheckboxField(default = true)
  )

  val email = Field(
    name = "email",
    caption = "Email",
    `type` = EmailField
  )

  val int = Field(
    name = "int",
    caption = "Int",
    `type` = IntField(min = Some(-10), max = Some(10), step = Some(2))
  )

  val tableCheckbox = Field(
    name = "tableCheckbox",
    caption = "TableCheckbox",
    `type` = TableCheckboxField(
      rows = List(TableCheckboxRow("a", "a"), TableCheckboxRow("b", "b")),
      cols = List(TableCheckboxCol("1", "1"), TableCheckboxCol("2", "2"))
    )
  )

  val tableCheckboxSimple = Field(
    name = "tableCheckboxSimple",
    caption = "TableCheckboxSimple",
    `type` = TableCheckboxField(
      rows = List(TableCheckboxRow("a", "a"), TableCheckboxRow("b", "b")),
      cols = List(TableCheckboxCol("1", "1"))
    )
  )

  val formProgress = Field(
    name = "formProgress",
    caption = "FormProgress",
    `type` = FormProgressField
  )
}