package controllers

import com.mongodb.casbah.MongoClient
import com.mongodb.casbah.commons.MongoDBObject
import com.mongodb.casbah.query.dsl.BSONType.DBList
import pl.pej.malpompaligxilo.form.field._
import pl.pej.malpompaligxilo.form.{Field, Header}
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
    val form = pl.pej.malpompaligxilo.jes2015.Jes2015Form.jesForm(field => post.getOrElse(field, Nil).headOption.getOrElse(""))

    val parsed = form.elements.filter{
      case f: Field[_] => f.visible(form)
      case _ => true
    }.collect{
      case f: Field[EnumOption] if f.`type`.isInstanceOf[SelectField] =>
        f.name -> f.parse(post.getOrElse(f.name, Seq.empty)).map(_.caption("eo"))
      case f: Field[Set[(TableCheckBoxRow, TableCheckBoxCol)]] if f.`type`.isInstanceOf[TableCheckBoxField] =>
        f.name -> f.parse(post.getOrElse(f.name+"[]", Seq.empty)).map{_.map{
          case (row, col) => row.id -> col.id
        }}
      case f: Field[_] =>
        f.name -> f.parse(post.getOrElse(f.name, Seq.empty))
    }.toMap

    val str = """Saluton! Dankon por via aliĝo al JES en Eger, Hungario!
                |Sube vi vidas viajn datumojn. Bv. relegi kaj nepre skribu al jes@pej.pl se io ne ĝustas, aŭ intertempe ŝanĝiĝas viaj mendoj.
                |Tion vi povas fari ĝis la 1-a de decembro.
                |Amike,
                |la teamo de JES
                |""".stripMargin + form.elements.collect{
      case f: Field[_] if f.visible(form) =>
        s"${f.caption("eo")}: ${parsed(f.name).getOrElse("")}"
      case h: Header =>
        s"\n\t${h.text("eo")}:"
    }.mkString("\n")


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
      bodyText = Some(str)
//      bodyHtml = Some("<html><body><p>An <b>html</b> message</p></body></html>")
    )

    MailerPlugin.send(email)
    val mongoClient = MongoClient()
    val mongoObj = MongoDBObject[String, Option[Any]](parsed.toList)
    val db = mongoClient.getDB("malpompaaligxilo")
    db("aligxintoj").insert(mongoObj)
    mongoClient.close()
    Ok(str)
  }
}
