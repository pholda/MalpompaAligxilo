package pl.pholda.malpompaaligxilo.googleapi

import java.net.URL

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.gdata.client.Query
import com.google.gdata.client.spreadsheet.SpreadsheetService
import com.google.gdata.data.spreadsheet._

import scala.collection.JavaConversions._

case class Spreadsheet(url: URL)(implicit config: AccountConfig) {

  protected[googleapi] lazy val service: SpreadsheetService = {
    val service = new SpreadsheetService("MySpreadsheetIntegration")
    service.setProtocolVersion(SpreadsheetService.Versions.V3)

    val JSON_FACTORY = JacksonFactory.getDefaultInstance
    val httpTransport = GoogleNetHttpTransport.newTrustedTransport()
    val credential = {
      val builder = new GoogleCredential.Builder()
        .setTransport(httpTransport)
        .setJsonFactory(JSON_FACTORY)
        .setServiceAccountId(config.serviceAccountId)
        .setServiceAccountScopes(
          List(
            "https://www.googleapis.com/auth/drive",
            "https://spreadsheets.google.com/feeds"
          ))

        {config match {
          case AccountConfigFile(_, file) =>
            builder.setServiceAccountPrivateKeyFromP12File(file)
          case AccountConfigKey(_, key) =>
            builder.setServiceAccountPrivateKey(key)
        }}.build()
    }

    service.setOAuth2Credentials(credential)
    service
  }

  protected[googleapi] lazy val feed = service.getFeed(Spreadsheet.feedUrl, classOf[SpreadsheetFeed])

  protected[googleapi] lazy val worksheetFeed: WorksheetFeed = service.getFeed(url, classOf[WorksheetFeed])
}

object Spreadsheet {
  protected val feedUrl = new URL("https://spreadsheets.google.com/feeds/spreadsheets/private/full")
}
