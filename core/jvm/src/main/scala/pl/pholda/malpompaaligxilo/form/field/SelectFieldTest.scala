package pl.pholda.malpompaaligxilo.form.field

import pl.pholda.malpompaaligxilo.i18n.NoI18nString
import utest._

object SelectFieldTest extends TestSuite {
  val tests = TestSuite{
    'validate{
      val field = SelectField(
        options = List(
          EnumOption("a", NoI18nString("AAA")),
          EnumOption("b", NoI18nString("BBB")),
          EnumOption("c", NoI18nString("CCC"))
        )
      )
      'emptyPost{
//        field.validate()
      }
    }
  }
}
