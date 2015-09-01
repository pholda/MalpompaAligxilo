package controllers

import pl.pholda.malpompaaligxilo.ContextJVM
import pl.pholda.malpompaaligxilo.dsl.parser.FormSpecificationParser
import pl.pholda.malpompaaligxilo.form.FormInstanceJVM
import pl.pholda.malpompaaligxilo.i18n.{I18nJVM, Lang}
import play.api.mvc._
import views.html

import scala.io.Source

object Main extends Controller {
  lazy val spec =
    Source.fromInputStream(getClass.getResourceAsStream("/specification.asl")).mkString

  implicit val i18n = I18nJVM.fromResources(getClass,
    "en" -> "/en.po",
    "pl" -> "/pl.po"
  )
  implicit val context = new ContextJVM()

  lazy val specJVM = {
    val parser = FormSpecificationParser()
    parser(spec).get
  }

  def index(implicit lang: Lang = "en") = Action {

    implicit val formInstance = new FormInstanceJVM(specJVM, {field =>
      Seq()
    })
 /*@()(implicit form: pl.pholda.malpompaaligxilo.form.FormInstanceJVM[_], lang: pl.pholda.malpompaaligxilo.i18n.Lang)*/
    Ok(html.index())
  }

  def submit = Action {
    Ok(":)")
  }

  def specification() = Action {
    Ok(spec)
  }

  def po(implicit lang: Lang = "en") = Action {
    Ok(html.po(i18n, lang))
  }
}
