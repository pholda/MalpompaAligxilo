package controllers

import com.mongodb.casbah.MongoClient
import com.mongodb.casbah.commons.MongoDBObject
import com.mongodb.casbah.query.dsl.BSONType.DBList
import pl.pej.malpompaligxilo.form.field._
import pl.pej.malpompaligxilo.form._
import pl.pej.malpompaligxilo.jes2015.Jes2015Form
import pl.pej.util.DatesScala
import play.api.libs.mailer.{Email, MailerPlugin}
import play.api.mvc._
import views._
import play.api.Play.current

object Application extends Controller {

  
  def index = Action {
    Ok(html.index())
  }

  def submit = Action(parse.tolerantFormUrlEncoded) { implicit request =>
    val post = request.body
    val form = Jes2015Form.jesForm(field => post.getOrElse(field, Nil), DatesScala)

    val parsed = form.parse(post)

    form.validate(parsed) match {
      case SuccessValidation =>
//        sendMail(form, parsed)
        mongoInsert(parsed)
        Ok(buildMessage(form, parsed))
      case FailureValidation(errors) =>
        Ok(s"okazis eraroj:\n${errors.map{
          case (f, e) => f.caption("eo") + ": " + e.message("eo")
        }.mkString("\n")}")
    }
  }

  private def mongoInsert(parsed: Map[String, Option[Any]]): Unit = {
    val mongoClient = MongoClient()
    val parsedMapped = parsed.mapValues{
      case Some(EnumOption(name, _)) => Some(name)
      case Some(s: Set[(TableCheckBoxRow, TableCheckBoxCol)]) => Some(s.map{
        case (r, c) => r.id -> c.id
      })
      case x => x
    }
    val mongoObj = MongoDBObject[String, Option[Any]](parsedMapped.toList)
    val db = mongoClient.getDB("malpompaaligxilo")
    db("aligxintoj").insert(mongoObj)
    mongoClient.close()
  }

  private def buildMessage(form: Form, parsed: Map[String, Option[Any]]): String = {
    """Saluton! Dankon por via aliĝo al JES en Eger, Hungario!
      |Sube vi vidas viajn datumojn. Bv. relegi kaj nepre skribu al jes@pej.pl se io ne ĝustas, aŭ intertempe ŝanĝiĝas viaj mendoj.
      |Tion vi povas fari ĝis la 1-a de decembro.
      |Amike,
      |la teamo de JES
      |""".stripMargin + form.elements.collect{
      case Field(_, caption, cf:CalculateField[_], _, _, _, _, _) =>
        s"${caption("eo")}: ${cf.formula(form).toString}"
      case f: Field[_] if f.visible(form) =>
        val v = parsed(f.name).getOrElse("") match {
          case s: String => s
          case EnumOption(_, caption) => caption("eo")
          case s:Set[(TableCheckBoxRow, TableCheckBoxCol)] => {
            if (s.isEmpty) {
              "[nenio]"
            } else {
              s.map{
                case (r, c) => r.caption("eo") + " " + c.caption("eo")
              }.mkString(", ")
            }
          }
          case _ => ""
        }

        s"${f.caption("eo")}: $v"
      case h: Header =>
        s"\n\t${h.text("eo")}:"
    }.mkString("\n")
  }

  private def sendMail(form: Form, parsed: Map[String, Option[Any]]): Unit = {
    val email = Email(
      subject = "Aliĝo al JES 2015 en Eger, Hungario",
      from = "JES-teamo <jes@pej.pl>",
      to = Seq(
        s"${parsed("personaNomo").get} ${parsed("familiaNomo").get} <${parsed("retposxtadreso").get}>"
      ),
      bcc = Seq(
        //        "jes@pej.pl",
        "holda.piotr@gmail.com"
      ),
      bodyText = Some(buildMessage(form, parsed))
      //      bodyHtml = Some("<html><body><p>An <b>html</b> message</p></body></html>")
    )
    MailerPlugin.send(email)
  }
}
