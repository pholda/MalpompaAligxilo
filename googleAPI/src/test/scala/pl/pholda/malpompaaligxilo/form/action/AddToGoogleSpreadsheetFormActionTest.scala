package pl.pholda.malpompaaligxilo.form.action

import java.io.File
import java.net.URL

import com.typesafe.config.{ConfigFactory, Config}
import pl.pholda.malpompaaligxilo.ContextJVM
import pl.pholda.malpompaaligxilo.form.{FormInstanceJVM, FormInstance}
import pl.pholda.malpompaaligxilo.googleapi.{AccountConfigFile, Spreadsheet}
import pl.pholda.malpompaaligxilo.i18n.NoTranslations
import utest._

object AddToGoogleSpreadsheetFormActionTest extends TestSuite {
  implicit val context = new ContextJVM()(NoTranslations)
  def formInstance = new FormInstanceJVM[TestForm.type](TestForm, {
    case TestForm.abc => Seq("abc value")
    case _ => Seq.empty
  })

  val conf = ConfigFactory.load("googleAPItest.conf")

  val tests = TestSuite{
    'writing{
      implicit val accountConfig = AccountConfigFile(
        conf.getString("serviceAccountId"),
        new File(conf.getString("p12"))
      )
      val spreadsheet = Spreadsheet(new URL(conf.getString("worksheetFeedUrl")))
      val action = AddToGoogleSpreadsheetFormAction(
        spreadsheet,
        conf.getString("worksheetTitle")
      )
      action.run(formInstance)
      val row = spreadsheet.readLastRow(conf.getString("worksheetTitle"), "abc" :: Nil)
      assert(row("abc") == "abc value")
    }
  }
}
