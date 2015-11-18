package pl.pholda.malpompaaligxilo

import pl.pholda.malpompaaligxilo.form.field.StringField
import pl.pholda.malpompaaligxilo.form.{Field, FormSpecification}
import pl.pholda.malpompaaligxilo.i18n.NoI18nString

object TestForm extends FormSpecification {
  override def fields: List[Field[_]] = abc :: Nil

  override def id: String = "testForm"

  val abc = Field(
    name = "abc",
    caption = NoI18nString("abc"),
    `type` = StringField()
  )
}
