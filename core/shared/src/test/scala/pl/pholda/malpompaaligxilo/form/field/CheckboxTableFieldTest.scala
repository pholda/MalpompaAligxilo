package pl.pholda.malpompaaligxilo.form.field

import pl.pholda.malpompaaligxilo.TestForm
import utest._

trait CheckboxTableFieldTest extends TestSuite with TestForm {

  import TestFormSpec._

  val tests = TestSuite{
    'selectedInTotal {
      val value = cbTable.value

      assert(value.get.selectedInTotal == 2)
    }
  }
}
