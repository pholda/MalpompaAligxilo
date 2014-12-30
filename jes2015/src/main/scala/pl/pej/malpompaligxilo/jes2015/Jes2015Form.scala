package pl.pej.malpompaligxilo.jes2015

import org.scalajs.dom
import org.scalajs.dom.Element
import org.scalajs.jquery.{JQueryEventObject, jQuery}
import pl.pej.malpompaligxilo.form.field._
import pl.pej.malpompaligxilo.form._
import pl.pej.malpompaligxilo.util.{NoI18nString, I18nString}
import pl.pej.util.{DatesJS, Dates}

import scala.collection.mutable.ListBuffer
import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport
import scala.scalajs.js.{JSApp, ThisFunction}
import scala.util.Try

object Jes2015Form extends JSApp {

  import TableCheckBoxField._

  def jesForm(getRawFieldValue: FieldName => Seq[String], dates: Dates): Form = {
    def kotizoExpr: FormExpr[Double] = {f =>
      val programKotizo = Seq(Seq(0,0,0),Seq(60,40,20),Seq(100,80,60))
      val balKotizo = 30
      val studentoRabato = 0.2
      val naskiita = dates.str2millis(f.getFieldValue("naskigxdato").get.asInstanceOf[String])
      //      new js.Date(f.getFieldValue("naskigxdato").get.asInstanceOf[String]).getTime()

      val agxkategorio = if(naskiita > dates.str2millis("1999-12-26")) 0
      else if(naskiita > dates.str2millis("1984-12-26")) 1
      else 2

      val currentDate = dates.getNowMillis//??? rok-miesiac-dzien i chyba zadziala ?

      val aligxkategorioRabato = if(currentDate < dates.str2millis("2015-01-30")) 0.4
      else if (currentDate < dates.str2millis("2015-04-30")) 0.32
      else if (currentDate < dates.str2millis("2015-07-30")) 0.24
      else if (currentDate < dates.str2millis("2015-11.30")) 0.16
      else 0

      val matenmangxoKotizo = 4.5
      val tagmangxoKotizo = 7
      val vespermangxoKotizo = 4.5

      val aligxkategorioMultiplikanto = 1 - aligxkategorioRabato

      val lando = f.getFieldValue("lando").get.asInstanceOf[EnumOption].value

      val aLandoj = Seq("ad", "at", "bh", "be", "gb", "dk", "fi", "fr", "de", "ie", "is", "il", "it", "jp", "ca", "qa", "kw", "li", "lu", "mc", "nl", "no", "om", "sm", "sa", "se", "ch", "ae", "us")

      val bLandoj = Seq(
        "al", "au", "ba", "bn", "bg", "cz", "ee", "gr", "es", "hu", "cy", "hr", "lt", "lv", "mk", "mt", "me", "nz", "pl", "pt", "ru", "rs", "sg", "sk", "si", "kr", "tw", "tr")

      val landoKategorio = if(aLandoj.contains(lando)) 0 else if (bLandoj.contains(lando)) 1 else 2

      val tuttempe: Boolean = f.getFieldValue("cxeesto").get.asInstanceOf[EnumOption].value == "cxiun"

      val cxeesto: Set[String] = f.getFieldValue("cxeesto-elekto").get.asInstanceOf[Set[(TableCheckBoxRow, TableCheckBoxCol)]].map{
        case (row, col) => row.id
      }.toSet

      val balo = if(tuttempe || cxeesto("31/1")) balKotizo else 0

      val studento: Boolean = f.getFieldValue("studento").get.asInstanceOf[EnumOption].value == "jes"

      val studentoMultiplikanto = if(studento) (1 - studentoRabato)
      else 1

      implicit def bool2int(b:Boolean) = if (b) 1 else 0

      val noktoj: Int = if (tuttempe) {
        7
      } else {
        cxeesto("27/28").toInt + cxeesto("28/29").toInt + cxeesto("29/30").toInt + cxeesto("30/31").toInt + cxeesto("31/1").toInt + cxeesto("1/2").toInt + cxeesto("2/3").toInt
      }



      val logxkotizoj = Seq(5, 12, 16)
      val dupersonaKrompago = 25


      val logxelekto = f.getFieldValue("logxado").get.asInstanceOf[EnumOption].value
      val logxkosto: Double = logxelekto match {
        case "2-lita-cxambro" => noktoj * logxkotizoj(2) + dupersonaKrompago
        case "4-5-lita-cxambro-dusxejo" => noktoj * logxkotizoj(2)
        case "4-5-lita-cxambro-sen-dusxejo" => noktoj * logxkotizoj(1)
        case "memzorge" => 0
        case _: String => noktoj * logxkotizoj(0)
      }

      val programo = programKotizo(landoKategorio)(agxkategorio)

      (logxkosto + balo + programo) * studentoMultiplikanto * aligxkategorioMultiplikanto
    }

    Form(
      "aligxilo",
      //      "submit",
      //      SimpleFormElementFormatter,
      getRawFieldValue,
      dates,
      Header(I18nString("eo" -> "Personaj datumoj")),
      Field(
        name = "personaNomo",
        caption = I18nString(
          "eo" -> "Persona Nomo",
          "pl" -> "Imie"
        ),
        required = true,
        `type` = StringField()
      ),
      Field(
        name = "familiaNomo",
        caption = I18nString(
          "eo" -> "Familia Nomo",
          "pl" -> "Imie"
        ),
        required = true,
        `type` = StringField()
      ),
      Field(
        name = "kromnomo",
        caption = I18nString(
          "eo" -> "Kromnomo",
          "pl" -> "Pseudonim"
        ),
        description = Some(I18nString(
          "eo" -> "Kion vi ŝatus aperigi sur via ŝildo?"
        )),
        `type` = StringField()
      ),
      Field(
        name = "naskigxdato",
        caption = I18nString(
          "eo" -> "Naskiĝdato",
          "pl" -> "Data urodzenia"
        ),
        required = true,
        `type` = DateField
      ),
      Field(
        name = "genro",
        caption = I18nString(
          "eo" -> "Genro",
          "pl" -> "Płeć"
        ),
        `type` = SelectField(
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
        )
      ),
      Field(
        name = "retposxtadreso",
        caption = I18nString(
          "eo" -> "Retpoŝtadreso",
          "pl" -> "Adres email"
        ),
        required = true,
        `type` = EmailField
      ),
      Field(
        name = "tujmesagxilo",
        caption = I18nString(
          "eo" -> "Tujmesaĝilo"
        ),
        `type` = StringField()
      ),
      Field(
        name = "telefonnumero",
        caption = I18nString(
          "eo" -> "Telefonnumero"
        ),
        placeholder = Some(I18nString("eo" -> "internacia formo (ekz. +36 …)")),
        description = Some(I18nString("eo" -> "Ni uzos ĝin nur por organizaj celoj (ekz. por retrovi vin, se vi perdiĝus).")),
        `type` = StringField()
      ),
      countryField,
      Field(
        name = "studento",
        caption = I18nString("eo" -> "Mi estas studento"),
        `type` = SelectField(
          options = List(
            EnumOption("ne", I18nString("eo" -> "ne")),
            EnumOption("jes", I18nString("eo" -> "jes"))
          )
        )
      ),
      Field(
        name = "invitilo",
        caption = I18nString("eo" -> "Mi bezonas invitilon"),
        description = Some(I18nString("eo" -> "Ŝtampita-subskribita invitletero.")),
        `type` = SelectField(
          options = List(
            EnumOption("ne", I18nString("eo" -> "ne")),
            EnumOption("jes", I18nString("eo" -> "jes"))
          )
        )
      ),
      Field(
        name = "pasportnumero",
        caption = I18nString("eo" -> "Numero de pasporto"),
        visible = _.getFieldValue("invitilo").asInstanceOf[Option[EnumOption]].exists(_.value == "jes"),
        `type` = StringField()
      ),
      Field(
        name = "pasportovalideco",
        caption = I18nString("eo" -> "Valideco de pasporto"),
        visible = _.getFieldValue("invitilo").asInstanceOf[Option[EnumOption]].exists(_.value == "jes"),
        `type` = DateField
      ),
      Field(
        name = "invitilo-adreso",
        caption = I18nString("eo" -> "Adreso"),
        description = Some(I18nString("eo" -> "Adreso por sendi la invitilon.")),
        visible = _.getFieldValue("invitilo").asInstanceOf[Option[EnumOption]].exists(_.value == "jes"),
        `type` = StringField(textarea = true)
      ),
      Field(
        name = "adresaro",
        caption = I18nString("eo" -> "Adresaro"),
        description = Some(I18nString("eo" -> "Post la aranĝo ni sendas al la partoprenantoj adresaron. Ĉu vi ŝatus aperi en ĝi? Se jes, kun kiuj datumoj?")),
        `type` = TableCheckBoxField(
          cols = List("jes" -> NoI18nString("jes")),
          rows = List(
            "personaNomo" -> I18nString("eo" -> "Persona Nomo"),
            "familiaNomo" -> I18nString("eo" -> "Familia Nomo"),
            "kromnomo" -> I18nString("eo" -> "Kromnomo"),
            "retposxtadreso" -> I18nString("eo" -> "Retpoŝtadreso"),
            "adreso" -> I18nString("eo" -> "Adreso"),
            "tujmesagxilo" -> I18nString("eo" -> "Tujmesaĝilo")
          ),
          default = true
        )
      ),
      Header(I18nString("eo" -> "Loĝado")),
      Field(
        name = "logxado",
        caption = I18nString(
          "eo" -> "Loĝado"
        ),
        //2-lita, 4-5 lita, amasloĝejo sur matraco, amasloĝejo surplanke
        `type` = SelectField(
          options = List(
            EnumOption("2-lita-cxambro", I18nString("eo" -> "2-lita ĉambro")),
            EnumOption("4-5-lita-cxambro-dusxejo", I18nString("eo" -> "4-5-lita ĉambro kun duŝejo")),
            EnumOption("4-5-lita-cxambro-sen-dusxejo", I18nString("eo" -> "4-5-lita ĉambro kun duŝejo fine de la koridoro")),
            EnumOption("amaslogxejo-matraco", I18nString("eo" -> "amasloĝejo sur matraco")),
            EnumOption("amaslogxejo-surplanke", I18nString("eo" -> "amasloĝejo surplanke")),
            EnumOption("memzorge", I18nString("eo" -> "mi mem zorgos"))
          )
        )
      ),
      Field(
        name = "logxado-gea",
        caption = I18nString(
          "eo" -> "Ĉu via ĉambro rajtas esti gea?"
        ),
        visible = _.getFieldValue("logxado").asInstanceOf[Option[EnumOption]].forall(_.value != "memzorge"),
        `type` = SelectField(
          options = List(
            EnumOption("ne", I18nString("eo" -> "ne")),
            EnumOption("jes", I18nString("eo" -> "jes"))
          )
        )
      ),
      Field(
        name = "logxado-prefero",
        caption = I18nString(
          "eo" -> "Mi preferas loĝi en … ĉambro."
        ),
        visible = _.getFieldValue("logxado").asInstanceOf[Option[EnumOption]].forall(_.value != "memzorge"),
        `type` = SelectField(
          options = List(
            EnumOption("egalas", I18nString("eo" -> "egalas")),
            EnumOption("trankvila", I18nString("eo" -> "trankvila")),
            EnumOption("dibocxa", I18nString("eo" -> "diboĉa"))
          )
        )
      ),
      Field(
        name = "logxado-kun",
        caption = I18nString(
          "eo" -> "Mi preferus loĝi kun…"
        ),
        visible = _.getFieldValue("logxado").asInstanceOf[Option[EnumOption]].forall(_.value != "memzorge"),
        `type` = StringField()
      ),
      Field(
        name = "cxeesto",
        caption = I18nString("eo" -> "Ĉeesto"),
        `type` = SelectField(List(
          EnumOption("cxiun", I18nString("eo" -> "ĉiun tagon")),
          EnumOption("elekto", I18nString("eo" -> "partatempe"))
        ))
      ),
      Field(
        name = "cxeesto-elekto",
        caption = I18nString("eo" -> ""),
        description = Some(I18nString("eo" -> "Bv. indiki la tranoktojn kiam vi partoprenos.")),
        visible = _.getFieldValue("cxeesto").asInstanceOf[Option[EnumOption]].exists(_.value == "elekto"),
        `type` = TableCheckBoxField(
          cols = List("jes" -> NoI18nString("jes")),
          rows = List(
            "27/28" -> NoI18nString("27/28"),
            "28/29" -> NoI18nString("28/29"),
            "29/30" -> NoI18nString("29/30"),
            "30/31" -> NoI18nString("30/31"),
            "31/1" -> NoI18nString("31/1"),
            "1/2" -> NoI18nString("1/2"),
            "2/3" -> NoI18nString("2/3")
          )
        )
      ),
      Header(I18nString("eo" -> "Manĝado")),
      Field(
        name = "mangxado",
        caption = I18nString(
          "eo" -> "Mi mendas…"
        ),
        `type` = SelectField(List(
          EnumOption("cxio", I18nString("eo" -> "ĉiujn manĝojn")),
          EnumOption("nenio", I18nString("eo" -> "neniujn manĝojn")),
          EnumOption("elekto", I18nString("eo" -> "unuopajn manĝojn"))
        ))
      ),
      Field(
        name = "mangxado-elekto",
        caption = I18nString(
          "eo" -> ""
        ),
        visible = _.getFieldValue("mangxado").asInstanceOf[Option[EnumOption]].exists(_.value == "elekto"),
        `type` = TableCheckBoxField(
          rows = List(
            "matenmangxo" -> I18nString(
              "eo" -> "Matenmanĝo"
            ),
            "tagmangxo" -> I18nString(
              "eo" -> "Tagmanĝo"
            ),
            "vespermangxo" -> I18nString(
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
            "matenmangxo" -> "27",
            "tagmangxo" -> "27",
            "tagmangxo" -> "3",
            "vespermangxo" -> "3"
          )
        )
      ),
      Field(
        name = "matenmangx-prefero",
        caption = I18nString(
          "eo" -> "Mi preferas matenmanĝi…"
        ),
        visible = _.getFieldValue("mangxado").asInstanceOf[Option[EnumOption]].forall(_.value != "nenio"),
        `type` = SelectField(
          options = List(
            EnumOption("ne_gravas", I18nString("eo" -> "ne gravas")),
            EnumOption("dolcxe", I18nString("eo" -> "dolĉe")),
            EnumOption("sale", I18nString("eo" -> "sale"))
          )
        )
      ),
      Field(
        name = "mangxtipo",
        caption = I18nString("eo" -> "Mi volas manĝi…"),
        visible = _.getFieldValue("mangxado").asInstanceOf[Option[EnumOption]].forall(_.value != "nenio"),
        `type` = SelectField(
          options = List(
            EnumOption("viande", I18nString("eo" -> "viande")),
            EnumOption("vegetare", I18nString("eo" -> "vegetare")),
            EnumOption("vegane", I18nString("eo" -> "vegane")),
            EnumOption("speciala", I18nString("eo" -> "specialan manĝon"))
          )
        )
      ),
      Field(
        name = "mangxtipo-klarigo",
        caption = NoI18nString(""),
        description = Some(I18nString("eo" -> "Bv klarigi kial vi volas specialan manĝon.")),
        visible = {form =>
          form.getFieldValue("mangxtipo").asInstanceOf[Option[EnumOption]].exists(_.value == "speciala") &&
            form.getFieldValue("mangxado").asInstanceOf[Option[EnumOption]].forall(_.value != "nenio")
        },
        `type` = StringField()
      ),
      Field(
        name = "mangxtipo2",
        caption = I18nString("eo" -> "Mi volas havi…"),
        visible = { form =>
          form.getFieldValue("mangxtipo").asInstanceOf[Option[EnumOption]].exists(_.value == "viande") &&
            form.getFieldValue("mangxado").asInstanceOf[Option[EnumOption]].forall(_.value != "nenio")
        },
        `type` = SelectField(
          options = List(
            EnumOption("viandega", I18nString("eo" -> "viandon kun viando! (mi volas havi viandaĵon por ĉiu plado)")),
            EnumOption("nenurvianda", I18nString("eo" -> "viandon kun aliaj nutraĵoj! (ĉiutage viandon, sed ne nur viandajn pladojn)"))
          )
        )
      ),
      Header(I18nString("eo" -> "Dum la aranĝo"), Some(I18nString("eo" -> "JES estas renkontiĝo, kie ricevas centran rolon la kontribuoj de la partoprenantoj.\nTiu ĉi renkontiĝo estas por vi. La organizantoj donas la strukturon de la programoj, kaj\nla partoprenantoj kontribuas al la programeroj dum la semajno ne nur per la ĉeestado.\nNi petas vin pripensi kiel vi povas mojosigi la etoson!"))),
      Field(
        name = "gxenerala-kontribuo",
        caption = I18nString("eo" -> "Mi kontribuos al la programo per"),
        `type` = TableCheckBoxField(
          rows = List(
            TableCheckBoxRow(
              "kunporti-ludilojn",
              I18nString("eo" -> "mi pretas kunporti mia(j)n plej ŝatata(j)n ludilojn por la ludejo.")
            ),
            TableCheckBoxRow(
              "lud-sesio",
              I18nString("eo" -> "Mi pretas gvidi ludsesiojn en la ludejo.")
            )
          ),
          cols = List(TableCheckBoxCol("jes", NoI18nString("jes")))
        )
      ),
//      Header(I18nString("eo" -> "Etoso")),
      Field(
        name = "ludi-muzikilon",
        caption = I18nString("eo" -> "Ĉu vi kantas, aŭ ludas iun muzikilon, kiun vi volonte kunportus?"),
        `type` = SelectField(List(
          EnumOption("jes", I18nString("eo" -> "jes")),
          EnumOption("ne", I18nString("eo" -> "ne"))
        ))
      ),
      Field(
        name = "kiel-ludos",
        caption = I18nString("eo" -> "Kiel vi volonte uzus vian kantkapablon / muzikilon?"),
        visible = _.getFieldValue("ludi-muzikilon").asInstanceOf[Option[EnumOption]].exists(_.value == "jes"),
        `type` = TableCheckBoxField(
          cols = List("jes" -> NoI18nString("jes")),
          rows = List(
            "mi-ludus" -> I18nString("eo" -> "Mi ne certas, sed mi volonte ludus aŭ sole, aŭ kune kun kelkaj aliaj dum la semajno"),
            "mi-prelegus" -> I18nString("eo" -> "Mi pretas prelegi pri kantado / muzikiloj / muziko / io ajn"),
            "mi-gvidus-kurson" -> I18nString("eo" -> "Mi pretas gvidi kurson pri kantado / muzikilludado"),
            "mi-koncertus-gufuje" -> I18nString("eo" -> "Mi pretas fari 20-30 minutan gufujan koncerteton"),
            "mi-koncertus-vespere" -> I18nString("eo" -> "Mi pretas aŭ sole, aŭ kun aliaj (propra muzikgrupo, spontanea ludado ktp.) fari 45-60 minutan koncerton, kiel vespera programo")
          )
        )
      ),
      Field(
        name = "mi-kunportos",
        caption = I18nString("eo" -> "Kiun muzikilon vi kunportos"),
        visible = _.getFieldValue("ludi-muzikilon").asInstanceOf[Option[EnumOption]].exists(_.value == "jes"),
        `type` = TableCheckBoxField(
          cols = List("jes" -> NoI18nString("jes")),
          rows = List(
            "gitaro" -> I18nString("eo" -> "Gitaro"),
            "violono" -> I18nString("eo" -> "Violono"),
            "fluto" -> I18nString("eo" -> "Fluto"),
            "ukulelo" -> I18nString("eo" -> "Ukulelo"),
            "alia" -> I18nString("eo" -> "Alia muzikilo"),
            "ne-scias" -> I18nString("eo" -> "Mi ankoraŭ ne decidis, sed mi certe kunportos ion")
          )
        )
      ),
      Field(
        name = "helpopropono",
        caption = I18nString("eo" -> "Helpopropono"),
        description = Some(I18nString("eo" -> "Se vi volonte helpos al ni pri ĝeneralaj taskoj bv. ĉi tie indiki (ekzemple per pakado de tabloj por ejo, gvidado de la grupo al la ekstera halo).")),
        `type` = StringField(textarea = true)
      ),
      Field(
        name = "programkontribuo",
        caption = I18nString("eo" -> "Programkontribuo"),
        description = Some(I18nString("eo" -> "Se vi ŝatus fari programeron, bv. skribu ĉi tien mallonge vian proponon!")),
        `type` = StringField(textarea = true)
      ),
      Header(I18nString("eo" -> "Pagado")),
      Field(
        name = "pagado",
        caption = I18nString("eo" -> "Pagado"),
        `type` = SelectField(List(
          EnumOption("uea", I18nString("eo" -> "UEA-konto")),
          EnumOption("hungara", I18nString("eo" -> "la hungara konto")),
          EnumOption("pola", I18nString("eo" -> "la pola konto"))
        ))
      ),
      Field(
        name = "donaco-kvoto",
        caption = I18nString("eo" -> "Mi ŝatus donaci al JES 2015…"),
        placeholder = Some(I18nString("eo" -> "entajpu kvoton (eŭroj)")),
        description = Some(I18nString("eo" -> "Se vi ŝatus subteni la eventon per iom da mono, ĝi certe bonvenas. :) Vi eĉ povas indiki, por kia celo ni elspezu ĝin!")),
        `type` = IntField(min = Some(0))
      ),
      Field(
        name = "donaco-kialo",
        caption = I18nString("eo" -> "Mi ŝatus donaci por"),
        `type` = SelectField(List(
          EnumOption("subteni-partoprenantojn", I18nString("eo" -> "subteni malriĉajn junajn partoprenantojn")),
          EnumOption("ricxa-programo", I18nString("eo" -> "pli riĉan programon")),
          EnumOption("alia", I18nString("eo" -> "alia"))
        ))
      ),
      Field(
        name = "donaco-kialo-klarigo",
        caption = NoI18nString(""),
        description = Some(I18nString("eo" -> "Bv klarigi.")),
        visible = _.getFieldValue("donaco-kialo").asInstanceOf[Option[EnumOption]].exists(_.value == "alia"),
        `type` = StringField()
      ),
      Field(
        name = "kotizo",
        caption = I18nString("eo" -> "Kalkulita kotizo"),
        `type` = CalculateField[Double](
          formula = kotizoExpr
        )
      ),
      Field(
        name = "mi-pagos-gxis",
        caption = I18nString("eo" -> "Mi pagos ĝis…"),
        `type` = DateField
      ),
      Field(
        name = "mi-volas-pagi",
        caption = I18nString("eo" -> "Mi volas pagi…"),
        description = Some(I18nString("eo" -> "Kiom da, en eŭroj.")),
        `type` = IntField(min = Some(0))
      )


    )
  }




  private val form: Form = jesForm({field =>
    Try{
      Seq(jQuery(s"#aligxilo [name=$field]").`val`().asInstanceOf[String])
    }.getOrElse{
      Try{
        val checked = new ListBuffer[String]
        jQuery(s"#aligxilo [name='$field[]']:checked").each{(a: js.Any, e: Element) =>
          checked.append(jQuery(e).`val`().asInstanceOf[String])
          a
        }
        checked.toSeq
      }.getOrElse(throw new Exception(s"cannot read field $field"))
    }

  }, DatesJS)

  @JSExport
  def main(): Unit = {
    val jqForm = jQuery(
      s"""
        |<form id="aligxilo" method="post" action="submit">
        |</form>
      """.stripMargin)
    form.elements.foreach{element =>
      jqForm.append(SimpleFormElementFormatter(element))
    }
    jqForm.append("""<input type="submit" value="Sendu" name="send" />""")
    //    form
    //    val jqForm =  form.toJQuery

    jQuery("body").append(jqForm)

    def refresh() {
      jqForm.find(".formExpression").each(new Function2[js.Any, dom.Element, js.Any]{
        override def apply(v1: js.Any, element: Element): js.Any = {
          jQuery(element).html(jQuery(element).data("expression").asInstanceOf[js.Function1[Form, js.Any]].apply(form).toString)
        }})

      jqForm.find(".row").each(new Function2[js.Any, dom.Element, js.Any] {
        override def apply(v1: js.Any, element: Element): js.Any = {
          jQuery(element).css("display", {
            val f = jQuery(element).data("visibleExpression")
            val x = Try(f.asInstanceOf[js.Function1[Form, Boolean]].apply(form)).getOrElse(true)
            if (x)
              "block"
            else
              "none"
          })
        }
      }
      )

    }

    jqForm.find(":input").change{e: dom.Element =>
      refresh()
    }


    refresh()

  }
}