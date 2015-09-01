package pl.pholda.malpompaaligxilo.dsl

import pl.pholda.malpompaaligxilo.form.{FormInstanceJVM, FormInstance}
import pl.pholda.malpompaaligxilo.{ContextJVM, Context}
import pl.pholda.malpompaaligxilo.i18n.{I18nJVM, TranslationProvider}

object TestFormJVM extends TestForm {
  override implicit val translationProvider: TranslationProvider = I18nJVM.fromResources(getClass,
      "en" -> "/en.po",
      "pl" -> "/pl.po")
  override implicit val context: ContextJVM = new ContextJVM()

  override val form: FormInstanceJVM[Form] = new FormInstanceJVM[Form](
    specification = new Form(),
    rawFieldValue = {
      case form.specification.a => Seq("value-a")
      case form.specification.date => Seq("2010-01-01")
      case form.specification.intField => Seq("123")
      case form.specification.selectField => Seq("1")
      case _ => Seq()
    }
  )(context)
}
