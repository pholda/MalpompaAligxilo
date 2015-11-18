package pl.pholda

import java.io.File
import java.net.URL

import com.typesafe.config.ConfigFactory
import pl.pholda.malpompaaligxilo.googleapi.{AccountConfigFile, Spreadsheet}

/**
  * Created by piotr on 31.10.15.
  */
package object malpompaaligxilo {

  val conf = ConfigFactory.load("googleAPItest.conf")

  implicit val accountConfig = AccountConfigFile(
    conf.getString("serviceAccountId"),
    new File(conf.getString("p12"))
  )
  val spreadsheet = Spreadsheet(new URL(conf.getString("worksheetFeedUrl")))
}
