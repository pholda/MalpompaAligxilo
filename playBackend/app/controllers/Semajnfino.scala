//package controllers
//
//import com.mongodb.casbah.MongoClient
//import com.mongodb.casbah.commons.MongoDBObject
//import org.joda.time.DateTime
//import pl.pej.malpompaaligxilo.semajnfino.SemajnfinoAligxilo
//import pl.pej.malpompaaligxilo.form.field.{TableCheckBoxRow, TableCheckBoxCol, EnumOption}
//import pl.pej.malpompaaligxilo.form.{FailureValidation, SuccessValidation}
//import pl.pej.util.DatesScala
//import play.api.libs.mailer.{MailerPlugin, Email}
//import play.api.mvc.{Action, Controller}
//import views.html
//import play.api.Play.current
//
//object Semajnfino extends Controller {
//
//  def index = Action {
//    val form = new SemajnfinoAligxilo(DatesScala, field => Seq.empty)
//    Ok(html.semajfninoAligxilo(form))
//  }
//
//  def submit = Action(parse.tolerantFormUrlEncoded) { implicit request =>
//    val post = request.body
//    val form = new SemajnfinoAligxilo(DatesScala, {field =>
//      post.getOrElse(field, post.getOrElse(field+"[]", Nil))
//    })
//
//    val parsed = form.parse(post)
//
//    form.validate(parsed) match {
//      case SuccessValidation =>
//        val msg = html.semajnfinoAligxiloMesagxo(form)
//        sendMail( s"${parsed("personaNomo").get} ${parsed("familiaNomo").get}", parsed("retposxtadreso").get.toString, msg.body)
//        mongoInsert(parsed, form)
//        Ok(html.semajnfinoAligxiloMesagxo(form))
//      case f:FailureValidation =>
//        Ok(html.semajnfinoAligxilo_eraroj(f))
//    }
//  }
//
//  private def mongoInsert(parsed: Map[String, Option[Any]], form: SemajnfinoAligxilo): Unit = {
//    val mongoClient = MongoClient()
//    val parsedMapped = parsed.mapValues {
//      case Some(EnumOption(name, _)) => Some(name)
//      case Some(s: Set[(TableCheckBoxRow, TableCheckBoxCol)]) => Some(s.map {
//        case (r, c) => r.id -> c.id
//      })
//      case x => x
//    } ++ List(
//      "aligxDato" -> Option(DateTime.now().toString("yyyy-MM-dd"))
//    )
//    val mongoObj = MongoDBObject[String, Option[Any]](parsedMapped.toList)
//    val db = mongoClient.getDB("pejsemajnfino")
//    db("aligxintoj").insert(mongoObj)
//    mongoClient.close()
//  }
//
//  private def sendMail(name: String, mail: String, text: String): Unit = {
//    val email = Email(
//      subject = s"Zapis na Weekend PEJowy | Aliĝo al PEJ-semajnfino - $name",
//      from = "PEJ <pej@pej.pl>",
//      to = Seq(
//        s"$name <$mail>"
//      ),
//      bcc = Seq(
//        "pej-estraro <estraro@pej.pl>",
//        "Romualda Jeziorowska <romualda.jeziorowska@pej.pl>",
//        "Piotr Hołda <piotr.holda@pej.pl>"
//      ),
//      bodyHtml = Some(text)
//    )
//    MailerPlugin.send(email)
//  }
//}
