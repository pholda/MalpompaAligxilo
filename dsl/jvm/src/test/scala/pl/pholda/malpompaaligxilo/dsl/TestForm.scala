package pl.pholda.malpompaaligxilo.dsl

import pl.pholda.malpompaaligxilo.ContextJVM
import pl.pholda.malpompaaligxilo.form.field.StringField
import pl.pholda.malpompaaligxilo.form.{Field, FormInstanceJVM, FormSpecification}
import pl.pholda.malpompaaligxilo.i18n.{I18nJVM, NoI18nString}

object TestForm {
  implicit val i18n = I18nJVM.fromResources(getClass,
    "en" -> "/en.po",
    "pl" -> "/pl.po")

  implicit val context = new ContextJVM()

  class Form extends FormSpecification {
    override def fields: List[Field[_]] = a :: Nil

    val a = Field(
      name = "a",
      caption = NoI18nString("a"),
      `type` = StringField()
    )

    override def id: String = "id"
  }

  lazy val form: FormInstanceJVM[Form] = new FormInstanceJVM[Form](
    specification = new Form(),
    rawFieldValue = {
      case form.specification.a => Seq("value-a")
      case _ => Seq()
    }
  )
}
