package pl.pholda.malpompaaligxilo.googleapi

import java.net.URL

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.gdata.client.Query
import com.google.gdata.client.spreadsheet.SpreadsheetService
import com.google.gdata.data.spreadsheet._

import scala.collection.JavaConversions._

case class Spreadsheet(worksheetFeedUrl: URL)(implicit config: AccountConfig) {

  protected lazy val service: SpreadsheetService = {
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

  /**
   * test purpose only
   * @param worksheetTitle
   * @param headers
   * @return
   */
  def readLastRow(worksheetTitle: String, headers: List[String]): Map[String, String] = {
    val spreadsheetFeedUrl = new URL("https://spreadsheets.google.com/feeds/spreadsheets/private/full")
    val feed = service.getFeed(spreadsheetFeedUrl, classOf[SpreadsheetFeed])

    val worksheetFeed: WorksheetFeed = service.getFeed(worksheetFeedUrl, classOf[WorksheetFeed])

    val worksheet = worksheetFeed.getEntries.find(_.getTitle.getPlainText == worksheetTitle).getOrElse{
      throw new Exception("worksheet not found!")
    }

    val query = service.query(new Query(worksheet.getListFeedUrl()), classOf[ListFeed])
    val customElements = query.getEntries().last.getCustomElements
    headers.map{header => header -> customElements.getValue(header)}.toMap
  }

  def insertRow(data: Map[String, String], worksheetTitle: String) = {
    val spreadsheetFeedUrl = new URL("https://spreadsheets.google.com/feeds/spreadsheets/private/full")
    val feed = service.getFeed(spreadsheetFeedUrl, classOf[SpreadsheetFeed])

    val worksheetFeed: WorksheetFeed = service.getFeed(worksheetFeedUrl, classOf[WorksheetFeed])

    val worksheet = worksheetFeed.getEntries.find(_.getTitle.getPlainText == worksheetTitle).getOrElse{
      throw new Exception("worksheet not found!")
    }

    val listFeedUrl = worksheet.getListFeedUrl()
    val listFeed = service.getFeed(listFeedUrl, classOf[ListFeed])

    var row = new ListEntry()
    data.foreach{
      case (k, v) => {
        row.getCustomElements.setValueLocal(k, v)
      }
    }
    service.insert(listFeedUrl, row)
  }
}
