package pl.pholda.malpompaaligxilo.form.db.google

import pl.pholda.malpompaaligxilo.googleapi.Worksheet
import pl.pholda.malpompaaligxilo.{TestForm, conf, spreadsheet}
import utest._

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

object WorksheetDbReaderTest extends TestSuite {
  val worksheet = Worksheet("WorksheetDbReaderTest", spreadsheet)
  val dbReader = new WorksheetDbReader(worksheet)
  val tests = TestSuite{
    'isUniqueValue{
      'isNotUnique{
        val result = Await.result(dbReader.isUniqueValue(TestForm.abc, "jeden"), 10 seconds)
        assert(!result)
      }
      'isUnique{
        val result = Await.result(dbReader.isUniqueValue(TestForm.abc, "dwa"), 10 seconds)
        assert(result)
      }
    }
  }
}
