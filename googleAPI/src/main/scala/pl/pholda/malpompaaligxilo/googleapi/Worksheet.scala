package pl.pholda.malpompaaligxilo.googleapi

import java.net.URL

import com.google.gdata.client.Query
import com.google.gdata.data.spreadsheet.{ListEntry, ListFeed, SpreadsheetFeed, WorksheetFeed}

import scala.collection.JavaConversions._
import scala.concurrent.{ExecutionContext, Future}

case class Worksheet(worksheetTitle: String, spreadsheet: Spreadsheet) {
  private def service = spreadsheet.service

  protected[googleapi] lazy val worksheet = spreadsheet.worksheetFeed.getEntries
    .find(_.getTitle.getPlainText == worksheetTitle).getOrElse{
      throw new Exception("worksheet not found!")
  }

  def existsRow(values: List[(String, String)])(implicit ec: ExecutionContext): Future[Boolean] = Future{
    val query = new Query(worksheet.getListFeedUrl)
    values.foreach{
      case (key, value) =>
        query.setStringCustomParameter("sq", s"$key=$value")
    }
    query.setMaxResults(1)

    val feed = service.query(query, classOf[ListFeed])
    println("Feed size  = " + feed.getEntries.size()+s", query = ${query.getUrl}")
    feed.getEntries.size() != 0
  }

  def insertRow(data: Map[String, String]): Unit = {
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

  /**
   *only test-purpose. If would be used in production - result should be a future
   */
  /*protected[googleapi] */def readLastRow(headers: List[String]): Map[String, String] = {
    val query = service.query(new Query(worksheet.getListFeedUrl), classOf[ListFeed])
    val customElements = query.getEntries().last.getCustomElements
    headers.map{header => header -> customElements.getValue(header)}.toMap
  }
}