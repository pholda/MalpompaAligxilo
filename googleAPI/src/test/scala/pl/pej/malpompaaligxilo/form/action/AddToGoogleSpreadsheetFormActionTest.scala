//package pl.pej.malpompaaligxilo.form.action
//
//import java.io.File
//import java.net.URL
//
//import org.scalatest.FunSuite
//import pl.pej.malpompaaligxilo.form.field.StringField
//import pl.pej.malpompaaligxilo.form.{ScalaContext, Field, Context, Form}
//import pl.pej.malpompaaligxilo.googleapi.{Spreadsheet, AccountConfig}
//import pl.pej.malpompaaligxilo.util.NoI18nString
//
//class AddToGoogleSpreadsheetFormActionTest extends FunSuite {
//  test("simple test") {
//    val form = new Form{
//      override val id: String = "id"
//
//      override protected def getRawFieldValue(field: Field[_]): Seq[String] = {
//        field match {
//          case this.name =>
//            Seq("Stefan")
//          case _ =>
//            Seq.empty
//        }
//      }
//
//      override def fields: List[Field[_]] = name :: Nil
//
//      override def isFilled: Boolean = true
//
//      override implicit val context: Context = ScalaContext
//
//      val name = Field(
//        name = "name",
//        caption = NoI18nString("Name"),
//        `type` = StringField()
//      )
//    }
//
//    implicit val accountConfig = AccountConfig(
//      serviceAccountId = "90211112486-s84fu8sqfg5hlnjcgglucup6nap73qnh@developer.gserviceaccount.com",
//      p12PrivateKey = new File("MalmpompaAligxilo-0f665c366cc1.p12")
//    )
//    val action = AddToGoogleSpreadsheetFormAction(
//      spreadsheet = Spreadsheet(
//        worksheetFeedUrl = new URL("https://spreadsheets.google.com/feeds/worksheets/1v9hB-6DYzR-VKQoPHbr6VfZFFVqcBTO8vX8vHoJ4mBc/private/full")
//      ),
//      worksheetTitle = "Arkusz1"
//    )
//    action(form)
//
//
//  }
//}
