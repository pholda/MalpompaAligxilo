package pl.pej.malpompaligxilo.generator

import org.scalajs.dom
import org.scalajs.dom.Element
import org.scalajs.jquery.{JQueryEventObject, jQuery}
import pl.pej.malpompaligxilo.form.field._
import pl.pej.malpompaligxilo.form.{FormExpression, Form}
import pl.pej.malpompaligxilo.util.{NoI18nString, I18nString}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport
import scala.scalajs.js.{JSApp, ThisFunction}

object Generator extends JSApp {



  @JSExport
  def main(): Unit = {
    val form = Form(
      "aligxilo",
      "http://192.168.56.101/aligxilo/AligxiloTest.php",
      SimpleFieldFormatter,
      StringField(
        name = "familiaNomo"
      ) @# I18nString(
        "eo" -> "Familia Nomo",
        "pl" -> "Nazwisko"
      ),
      StringField(
        name = "personaNomo"
      ) @# I18nString(
        "eo" -> "Persona Nomo",
        "pl" -> "Imie"
      ),
      StringField(
        name = "kromnomo"
      ) @## (I18nString(
        "eo" -> "Kromnomo",
        "pl" -> "Pseudonim"
      ),I18nString(
        "eo" -> "kion vi ŝatus aperigi sur via ŝildo?"
      )),
      DateField(
        name = "naskigxdato"
      ) @# I18nString(
        "eo" -> "Naskiĝdato",
        "pl" -> "Data urodzenia"
      ),
      SelectField(
        name = "genro",
        options = List(
          EnumOption(
            value = "ina",
            caption = I18nString(
              "eo" -> "Ina",
              "pl" -> "Żeńska"
            )
          ),
          EnumOption(
            value = "malina",
            caption = I18nString(
              "eo" -> "Malina",
              "pl" -> "Męska"
            )
          ),
          EnumOption(
            value = "alia",
            caption = I18nString(
              "eo" -> "Alia",
              "pl" -> "Inna"
            )
          )
        )
      ) @# I18nString(
        "eo" -> "Genro",
        "pl" -> "Płeć"
      ),
      EmailField(
        name = "retposxtadreso"
      ) @# I18nString(
        "eo" -> "Retpoŝtadreso",
        "pl" -> "Adres email"
      ),
      countryField,
      SelectField(
        name = "logxado",
        options = List(
          EnumOption("cxambro", I18nString("eo" -> "ĉambro")),
          EnumOption("amaslogxejo", I18nString("eo" -> "amasloĝejo"))
        )
      ) @# I18nString(
        "eo" -> "Loĝado"
      ),
      TableCheckBoxField(
        name = "mangxado",
        rows = List(
          "m" -> I18nString(
            "eo" -> "Matenmanĝo"
          ),
          "t" -> I18nString(
            "eo" -> "Tagmanĝo"
          ),
          "v" -> I18nString(
            "eo" -> "Vespermanĝo"
          )
        ),
        cols = List(
          "27" -> NoI18nString("27.12"),
          "28" -> NoI18nString("28.12"),
          "29" -> NoI18nString("29.12"),
          "30" -> NoI18nString("30.12"),
          "31" -> NoI18nString("31.12"),
          "1" -> NoI18nString("1.01"),
          "2" -> NoI18nString("2.01"),
          "3" -> NoI18nString("3.01")
        ),
        disabled = List(
          "m" -> "27",
          "t" -> "27",
          "t" -> "3",
          "v" -> "3"
        )
      ) @# I18nString(
        "eo" -> "Mi mendas..."
      ),
      SelectField(
        name = "matenmangxPrefero",
        options = List(
          EnumOption("dolcxe", I18nString("eo" -> "dolĉe")),
          EnumOption("sale", I18nString("eo" -> "sale"))
        )
      ) @# I18nString(
        "eo" -> "Mi preferas matenmanĝi..."
      ),
      SelectField(
        name = "mangxTipo",
        options = List(
          EnumOption("viande", I18nString("eo" -> "viande")),
          EnumOption("vegetare", I18nString("eo" -> "vegetare")),
          EnumOption("vegane", I18nString("eo" -> "vegane")),
          EnumOption("specialan", I18nString("eo" -> "specialan manĝon"))
        )
      ) @# I18nString("eo" -> "Mi volas manĝi..."),
      SelectField(
        name = "mangxoTipo2",
        options = List(
          EnumOption("a", I18nString("eo" -> "viandon kun viando!")),
          EnumOption("b", I18nString("eo" -> "viandon kun aliaj nutraĵoj!"))
        )
      //viandon kun viando! (mi volas havi viandaĵon por ĉiu plado) |
      // viandon kun aliaj nutraĵoj (ĉiutage viandon, sed ne nur viandajn pladojn - ekz. senvianda supo + vianda ĉefplado) |

      ) @# I18nString("eo" -> "Mi volas havi..."),
      StringField(
        name = "programkontribuo",
        textarea = true
      ) @## (
        I18nString("eo" -> "Programkontribuo"),
        I18nString("eo" -> "Se vi ŝatus fari programeron, bv. skribu ĉi tien mallonge vian proponon!")
      ),
      StringField(
        name = "donaco"
      ) @## (
        I18nString("eo" -> "Mi ŝatus donaci al JES 2015..."),
        I18nString("eo" -> "Se vi ŝatus subteni la eventon per iom da mono, ĝi certe bonvenas. :) Vi eĉ povas indiki, por kia celo ni elspezu ĝin!")
      ),
      SelectField(
        name = "invitilo",
        options = List(
          EnumOption("jes", I18nString("eo" -> "jes")),
          EnumOption("ne", I18nString("eo" -> "ne"))
        )
      ) @# I18nString("eo" -> "Invitilo"),
      TableCheckBoxField(
        name = "adresaro",
        cols = List("jes" -> NoI18nString("jes")),
        rows = List(
          "personaNomo" -> I18nString("eo" -> "Persona Nomo"),
          "familiaNomo" -> I18nString("eo" -> "Familia Nomo"),
          "kromnomo" -> I18nString("eo" -> "Kromnomo"),
          "retposxtadreso" -> I18nString("eo" -> "Retpoŝtadreso"),
          "adreso" -> I18nString("eo" -> "Adreso")
        )
      ) @## (
        I18nString("eo" -> "Adresaro"),
        I18nString("eo" -> "Post la aranĝo ni sendas al la partoprenantoj adresaron. Ĉu vi ŝatus aperi en ĝi? Se jes, kun kiuj datumoj?")
      )/*,

      CalculateField[String](
        name = "agxo",
        formula = new FormExpression[String]{

          override def apply(form: Form): String = {
            form.getFieldValue("familiaNomo").toString * 2
          }
        }
      ) @# I18nString(
        "eo" -> "2·nomo"
      )*/

    )

    val jqForm = form.toJQuery

    jQuery("body").append(jqForm)

    def refresh() {
      jqForm.find(".formExpression").each(new Function2[js.Any, dom.Element, js.Any]{
        override def apply(v1: js.Any, element: Element): js.Any = {
          jQuery(element).html(jQuery(element).data("expression").asInstanceOf[js.Function1[Form, String]].apply(form))
        }})
    }

    jqForm.find(":input").change{e: dom.Element =>
      refresh()
    }

  }
}