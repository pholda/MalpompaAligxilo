package controllers

import pl.pej.malpompaaligxilo.form.field.StringField
import pl.pej.malpompaaligxilo.form.{SuccessValidation, FailureValidation, Field, FilledField}
import pl.pej.malpompaaligxilo.util.{NoI18nString, DatesScala}
import play.api.mvc._
import views.html

object SimpleForm extends Controller {
  import pl.pej.malpompaaligxilo.examples.simple.SimpleForm

  def index = Action {
    val form = new SimpleForm(field => Seq.empty)(DatesScala)

    Ok(html.index(form))
  }

  def submit = Action(parse.tolerantFormUrlEncoded) { implicit request =>
    val post = request.body
    val form = new SimpleForm({field =>
      post.getOrElse(field, post.getOrElse(field+"[]", Nil))
    })(DatesScala)

    form.hasErrors match {
      case true =>
        Ok(html.index(form))
      case false =>
        Ok(html.success(form))
    }
  }
}
