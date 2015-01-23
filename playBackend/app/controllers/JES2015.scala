package controllers

import java.text.DecimalFormat

import com.mongodb.casbah.MongoClient
import com.mongodb.casbah.commons.MongoDBObject
import org.joda.time.DateTime
import pl.pej.malpompaaligxilo.form._
import pl.pej.malpompaaligxilo.form.field._
import pl.pej.malpompaaligxilo.jes2015.Jes2015Aligxilo
import pl.pej.util.DatesScala
import play.api.Play.current
import play.api.libs.mailer.{Email, MailerPlugin}
import play.api.mvc._
import views._

object JES2015 extends Controller {

  
  def index = Action {
    val form = new Jes2015Aligxilo(DatesScala, field => Seq.empty)
    Ok(html.jes2015aligxilo(form)(f => FilledField(f, None, None)))
  }

  def submit = Action(parse.tolerantFormUrlEncoded) { implicit request =>
    val post = request.body
    val form = new Jes2015Aligxilo(DatesScala, {field =>
      post.getOrElse(field, post.getOrElse(field+"[]", Nil))
    })

    val parsed = form.parse(post)

    parsed.validate match {
      case SuccessValidation =>
        val msg = html.jes2015_aligxilo_mesagxo(form)
        sendMail( s"${parsed.fields(form.personaNomo).value.get} ${parsed.fields(form.familiaNomo).value.get}",
          parsed.fields(form.retposxtadreso).value.get.toString, msg.body)
        mongoInsert(parsed.data.map{field => field.name -> field.value}.toMap, form)
        Ok(html.jes2015_aligxilo_sukceso(form))
      case f:FailureValidation =>
        Ok(html.jes2015aligxilo(form)(field => parsed.fields(field)))
    }
  }

  private def mongoInsert(parsed: Map[String, Option[Any]], form: Jes2015Aligxilo): Unit = {
    val mongoClient = MongoClient()
    val parsedMapped = parsed.mapValues {
      case Some(EnumOption(name, _)) => Some(name)
      case Some(s: Set[(TableCheckBoxRow, TableCheckBoxCol)]) => Some(s.map {
        case (r, c) => r.id -> c.id
      })
      case x => x
    } ++ List(
      "aligxDato" -> Option(DateTime.now().toString("yyyy-mm-dd"))
    )
    val mongoObj = MongoDBObject[String, Option[Any]](parsedMapped.toList)
    val db = mongoClient.getDB("jes2015")
    db("aligxintoj").insert(mongoObj)
    mongoClient.close()
  }

  private def sendMail(name: String, mail: String, text: String): Unit = {
    val email = Email(
      subject = s"Aliĝo al JES 2015 en Eger, Hungario - $name",
      from = "JES-teamo <jes@pej.pl>",
      to = Seq(
        s"$name <$mail>"
      ),
      bcc = Seq(
        "<hej.estraro@gmail.com>",
        "<tomasz.szymula@pej.pl>",
        "<piotr.holda@pej.pl>"
      ),
      bodyHtml = Some(text)
    )

    MailerPlugin.send(email)

    val email2 = Email(
      subject = s"Homo aliĝis al JES 2015 en Eger, Hungario - $name",
      from = "JES-teamo <jes@pej.pl>",
      to = Seq(
        "<hej.estraro@gmail.com>",
        "<holda.piotr@gmail.com>"
      ),
      bodyHtml = Some(text)
    )

    MailerPlugin.send(email2)
  }
}
