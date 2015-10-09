package pl.pholda.malpompaaligxilo.form.field

import pl.pholda.malpompaaligxilo.TestForm
import utest._

trait CostFieldTest extends TestSuite with TestForm {

  import TestFormSpec._

  val tests = TestSuite{
    'singleCost{
      assert(singleCost.value.get.total == 50)
    }
    'multipleCost{
      assert(multipleCost.value.get.total == 1230)
    }
  }
}
