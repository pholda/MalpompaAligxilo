package controllers

import pl.pholda.malpompaaligxilo.form.ScalaContext
import play.api.mvc._
import views.html

object SimpleForm extends Controller {
  implicit val context = ScalaContext

  import pl.pholda.malpompaaligxilo.examples.simple.SimpleForm

  def index = Action {
    val form = new SimpleForm(
      isFilled = false,
      rawFieldValue =  field => Seq.empty
    )

    Ok(html.index(form))
  }

  def submit = Action(parse.tolerantFormUrlEncoded) { implicit request =>
    val post = request.body
    val form = new SimpleForm(
      isFilled = true,
      rawFieldValue = field =>
      post.getOrElse(field.name, post.getOrElse(field+"[]", Nil))
    )

    form.hasErrors match {
      case true =>
        Ok(html.index(form))
      case false =>
        Ok(html.success(form))
    }
  }
}
