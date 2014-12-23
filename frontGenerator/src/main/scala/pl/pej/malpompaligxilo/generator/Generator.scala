package pl.pej.malpompaligxilo.generator

import org.scalajs.dom
import org.scalajs.dom.Element
import org.scalajs.jquery.{JQueryEventObject, jQuery}
import pl.pej.malpompaligxilo.form.field._
import pl.pej.malpompaligxilo.form.{Expression, Form}
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
      StringField(
        name = "familiaNomo",
        caption = I18nString(
          "eo" -> "Familia Nomo",
          "pl" -> "Nazwisko"
        )
      ),
      StringField(
        name = "personaNomo",
        caption = I18nString(
          "eo" -> "Persona Nomo",
          "pl" -> "Imie"
        )
      ),
      StringField(
        name = "kromnomo",
        caption = I18nString(
          "eo" -> "Kromnomo",
          "pl" -> "Pseudonim"
        ),
        description = Some(I18nString(
          "eo" -> "kion vi ŝatus aperigi sur via ŝildo?"
        ))
      ),
      DateField(
        name = "naskigxdato",
        caption = I18nString(
          "eo" -> "Naskiĝdato",
          "pl" -> "Data urodzenia"
        )
      ),
      SelectField(
        name = "genro",
        caption = I18nString(
          "eo" -> "Genro",
          "pl" -> "Płeć"
        ),
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
      ),
      EmailField(
        name = "retposxtadreso",
        caption = I18nString(
          "eo" -> "Retpoŝtadreso",
          "pl" -> "Adres email"
        )
      ),
      countryField.copy(size = 1),
      SelectField(
        name = "logxado",
        caption = I18nString(
          "eo" -> "Loĝado"
        ),
        options = List(
          EnumOption("cxambro", I18nString("eo" -> "ĉambro")),
          EnumOption("amaslogxejo", I18nString("eo" -> "amasloĝejo"))
        )
      ),
      TableCheckBoxField(
        name = "mangxado",
        caption = I18nString(
          "eo" -> "Mi mendas..."
        ),
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
      ),
      SelectField(
        name = "matenmangxPrefero",
        caption = I18nString(
          "eo" -> "Mi preferas matenmanĝi..."
        ),
        options = List(
          EnumOption("dolcxe", I18nString("eo" -> "dolĉe")),
          EnumOption("sale", I18nString("eo" -> "sale"))
        )
      ),
      SelectField(
        name = "mangxTipo",
        caption = I18nString("eo" -> "Mi volas manĝi..."),
        options = List(
          EnumOption("viande", I18nString("eo" -> "viande")),
          EnumOption("vegetare", I18nString("eo" -> "vegetare")),
          EnumOption("vegane", I18nString("eo" -> "vegane")),
          EnumOption("specialan", I18nString("eo" -> "specialan manĝon"))
        )
      ),
      SelectField(
        name = "mangxoTipo2",
        caption = I18nString("eo" -> "Mi volas havi..."),
        options = List(
          EnumOption("a", I18nString("eo" -> "viandon kun viando!")),
          EnumOption("b", I18nString("eo" -> "viandon kun aliaj nutraĵoj!"))
        )
      //viandon kun viando! (mi volas havi viandaĵon por ĉiu plado) |
      // viandon kun aliaj nutraĵoj (ĉiutage viandon, sed ne nur viandajn pladojn - ekz. senvianda supo + vianda ĉefplado) |

      ),
      StringField(
        name = "programkontribuo",
        caption = I18nString("eo" -> "Programkontribuo"),
        description = Some(I18nString("eo" -> "Se vi ŝatus fari programeron, bv. skribu ĉi tien mallonge vian proponon!")),
        textarea = true
      ),
      StringField(
        name = "donaco",
        caption = I18nString("eo" -> "Mi ŝatus donaci al JES 2015..."),
        description = Some(I18nString("eo" -> "Se vi ŝatus subteni la eventon per iom da mono, ĝi certe bonvenas. :) Vi eĉ povas indiki, por kia celo ni elspezu ĝin!"))
      ),
      SelectField(
        name = "invitilo",
        caption = I18nString("eo" -> "Invitilo"),
        options = List(
          EnumOption("jes", I18nString("eo" -> "jes")),
          EnumOption("ne", I18nString("eo" -> "ne"))
        )
      ),
      TableCheckBoxField(
        name = "adresaro",
        caption = I18nString("eo" -> "Adresaro"),
        description = Some(I18nString("eo" -> "Post la aranĝo ni sendas al la partoprenantoj adresaron. Ĉu vi ŝatus aperi en ĝi? Se jes, kun kiuj datumoj?")),
        cols = List("jes" -> NoI18nString("jes")),
        rows = List(
          "personaNomo" -> I18nString("eo" -> "Persona Nomo"),
          "familiaNomo" -> I18nString("eo" -> "Familia Nomo"),
          "kromnomo" -> I18nString("eo" -> "Kromnomo"),
          "retposxtadreso" -> I18nString("eo" -> "Retpoŝtadreso"),
          "adreso" -> I18nString("eo" -> "Adreso")
        )
      ),

      CalculateField[String](
        name = "agxo",
        caption = I18nString(
          "eo" -> "2·nomo"
        ),
        formula = new Expression[String]{

          override def apply(form: Form): String = {
            form.getFieldValue("familiaNomo").toString * 2
          }
        }
      )

    )

    val jqForm = form.toHTML

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

//    jQuery().date
//    if (!Modernizr.inputtypes.date) {
//      $('input[type=date]').datepicker({
//        // Consistent format with the HTML5 picker
//        dateFormat: 'yy-mm-dd'
//      });
//    }

  }
}