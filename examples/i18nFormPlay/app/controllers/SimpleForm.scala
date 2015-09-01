package controllers

import pl.pholda.malpompaaligxilo.ContextJVM
import pl.pholda.malpompaaligxilo.form.FormInstanceJVM
import pl.pholda.malpompaaligxilo.i18n.{I18nJVM, Lang}
import play.api.mvc._
import views.html
import pl.pholda.malpompaaligxilo.examples.i18n.I18nFormSpec

object SimpleForm extends Controller {
  implicit val i18n = I18nJVM.fromResources(getClass,
      "pl" -> "/form_pl.po",
      "eo" -> "/form_eo.po",
      "en" -> "/form_en.po"
    )

  implicit val context = new ContextJVM()

  def index(implicit lang: Lang = "en") = Action {
    val spec = new I18nFormSpec()
    implicit val formInstance = new FormInstanceJVM(spec, {field =>
      Seq.empty
    })

    Ok(html.index())
  }

  def submit = Action(parse.tolerantFormUrlEncoded) { implicit request =>
    Ok("")
//    val post = request.body
//    val form = new I18nForm(
//      isFilled = true,
//      rawFieldValue = field =>
//      post.getOrElse(field.name, post.getOrElse(field+"[]", Nil))
//    )
//
//    form.hasErrors match {
//      case true =>
//        Ok(html.index(form, "eo", poCfg))
//      case false =>
//        Ok(html.success(form))
//    }
  }
}
