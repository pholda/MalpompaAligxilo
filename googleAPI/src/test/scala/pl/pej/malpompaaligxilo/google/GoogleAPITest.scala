package pl.pej.malpompaaligxilo.google


import java.io.File
import java.net.URL
import java.util
import java.util.Collections

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.gdata.client.spreadsheet.SpreadsheetService
import com.google.gdata.data.PlainTextConstruct
import com.google.gdata.data.spreadsheet._
import org.scalatest.FunSuite

import collection.JavaConversions._

//notasecret

class GoogleAPITest extends FunSuite {
  test("aaa") {
    val service = new SpreadsheetService("MySpreadsheetIntegration")
    service.setProtocolVersion(SpreadsheetService.Versions.V3)

    val emailAddress = "90211112486-s84fu8sqfg5hlnjcgglucup6nap73qnh@developer.gserviceaccount.com"
    val JSON_FACTORY = JacksonFactory.getDefaultInstance()
    val httpTransport = GoogleNetHttpTransport.newTrustedTransport()
    val credential = new GoogleCredential.Builder()
      .setTransport(httpTransport)
      .setJsonFactory(JSON_FACTORY)
      .setServiceAccountId(emailAddress)
      .setServiceAccountPrivateKeyFromP12File(new File("MalmpompaAligxilo-0f665c366cc1.p12"))
      .setServiceAccountScopes(
        List(
          "https://www.googleapis.com/auth/drive",
          "https://spreadsheets.google.com/feeds"
        ))
      .build()

    service.setOAuth2Credentials(credential)

    val spreadsheetFeedUrl = new URL("https://spreadsheets.google.com/feeds/spreadsheets/private/full")


    val feed = service.getFeed(spreadsheetFeedUrl, classOf[SpreadsheetFeed])
    val spreadsheets = feed.getEntries()

    println(spreadsheets)

    val spreadsheet = spreadsheets.head

    val worksheetFeed = service.getFeed(
      spreadsheet.getWorksheetFeedUrl(), classOf[WorksheetFeed])

    val worksheets = worksheetFeed.getEntries()
    val worksheet = worksheets.get(0)

    val listFeedUrl = worksheet.getListFeedUrl()
    val listFeed = service.getFeed(listFeedUrl, classOf[ListFeed])

    // Create a local representation of the new row.
    var row = new ListEntry()
//    row.setContent(new PlainTextConstruct("blablabla"))
    row.getCustomElements().setValueLocal("aaa", "Joe")
//    row.getCustomElements().setValueLocal("lastname", "Smith")
//    row.getCustomElements().setValueLocal("age", "26")
//    row.getCustomElements().setValueLocal("height", "176")

    // Send the new row to the API for insertion.
    service.insert(listFeedUrl, row)


    println(spreadsheet.getTitle().getPlainText())
    println(spreadsheet.getSpreadsheetLink.getHref)

    println(worksheet.getTitle.getPlainText)
  }
}
