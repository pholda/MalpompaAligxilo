package pl.pholda.malpompaaligxilo

import pl.pholda.malpompaaligxilo.form.FormInstanceJVM
import pl.pholda.malpompaaligxilo.i18n.{I18nJVM, TranslationProvider}

trait TestFormJVM extends TestForm {
  override implicit val translationProvider: TranslationProvider = I18nJVM.fromResources(getClass,
      "en" -> "/en.po",
      "pl" -> "/pl.po")
  override implicit val context: ContextJVM = new ContextJVM()

  override val form: FormInstanceJVM[TestFormSpec.type ] = new FormInstanceJVM[TestFormSpec.type ](
    specification = TestFormSpec,
    rawFieldValue = {
      case form.specification.a => Seq("value-a")
      case form.specification.date => Seq("2010-01-01")
      case form.specification.intField => Seq("123")
      case form.specification.selectField => Seq("1")
      case form.specification.cbTable => Seq("a-1", "b-1", "c-1")
      case _ => Seq()
    }
  )(context)
}
