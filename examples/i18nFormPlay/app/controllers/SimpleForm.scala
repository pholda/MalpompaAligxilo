package controllers

import pl.pej.malpompaaligxilo.examples.i18n._
import pl.pej.malpompaaligxilo.form.ScalaContext
import pl.pej.malpompaaligxilo.util.{PoCfg, Lang}
import play.api.mvc._
import views.html

object SimpleForm extends Controller {
  implicit val context = ScalaContext

  implicit val poCfg = PoCfg.fromResources(getClass,
    "pl" -> "/form_pl.po",
    "eo" -> "/form_eo.po",
    "en" -> "/form_en.po"
  )

  import pl.pej.malpompaaligxilo.examples.i18n.I18nForm

  def index(implicit lang: Lang = "en") = Action {
    val form = new I18nForm(
      isFilled = false,
      rawFieldValue =  field => Seq.empty
    )

    Ok(html.index(form, lang, poCfg))
  }

  def submit = Action(parse.tolerantFormUrlEncoded) { implicit request =>
    val post = request.body
    val form = new I18nForm(
      isFilled = true,
      rawFieldValue = field =>
      post.getOrElse(field.name, post.getOrElse(field+"[]", Nil))
    )

    form.hasErrors match {
      case true =>
        Ok(html.index(form, "eo", poCfg))
      case false =>
        Ok(html.success(form))
    }
  }
}
