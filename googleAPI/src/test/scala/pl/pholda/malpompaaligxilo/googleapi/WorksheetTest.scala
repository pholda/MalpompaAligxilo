package pl.pholda.malpompaaligxilo.googleapi

import java.io.File
import java.net.URL

import com.typesafe.config.ConfigFactory
import utest._

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

object WorksheetTest extends TestSuite{

  val conf = ConfigFactory.load("googleAPItest.conf")

  implicit val accountConfig = AccountConfigFile(
    conf.getString("serviceAccountId"),
    new File(conf.getString("p12"))
  )

  val spreadsheet = Spreadsheet(new URL(conf.getString("worksheetFeedUrl")))

  val tests = TestSuite{
    val worksheet = Worksheet("worksheetTest1", spreadsheet)
    'existsTest{
      'exists {
        assertMatch(Await.result(worksheet.existsRow(List("a" -> "x")), 10 seconds)) {
          case true =>
        }
      }
      'exists2{
        assertMatch(Await.result(worksheet.existsRow(List("a" -> "ąę")), 10 seconds)) {
          case false =>
        }
      }
    }
  }
}
