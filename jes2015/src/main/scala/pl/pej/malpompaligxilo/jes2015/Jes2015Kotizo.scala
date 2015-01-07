package pl.pej.malpompaligxilo.jes2015

object Jes2015Kotizo {
  type Euroj = Double

  implicit def bool2int(b:Boolean) = if (b) 1 else 0

  object Prezoj {

    val studentaRabato = 0.15
    val programo: Seq[Seq[Euroj]] = Seq(Seq(0,0,0),Seq(100,40,20),Seq(120,110,100))

    val matenmangxo: Euroj = 5
    val tagmangxo: Euroj = 8
    val vespermangxo: Euroj = 5


    val tranokto: Seq[Euroj] = Seq(6, 12, 16)
    val dulitaKrompago: Euroj = 30

    val imposto: Euroj = 1.5
  }

  def kotizo(form: Jes2015Aligxilo) = {
    def rabataMultiplikanto: Double = {
      1 - frualigxaRabato - studentaRabato
    }

    def frualigxaRabato: Double = {

      if (form.getFieldValue(form.invitilo).exists(_.value == "jes")) {
        val pagoElekto: String = form.getFieldValue(form.miPagosGxis).get.value

        val currentDate = form.dates.getNowMillis

        val aligxkategorioRabato = if(currentDate < form.dates.str2millis("2015-01-30")) 0.3
        else if (currentDate < form.dates.str2millis("2015-04-30")) 0.24
        else if (currentDate < form.dates.str2millis("2015-07-30")) 0.18
        else if (currentDate < form.dates.str2millis("2015-11.30")) 0.12
        else 0

        pagoElekto match {
          case "nurAntaupago" => 0
          case "tutaPago" => aligxkategorioRabato
        }
      } else {
        val pagoElekto: String = form.getFieldValue(form.miPagosGxis).get.value

        pagoElekto match {
          case "rabato30" => 0.3
          case "rabato24" => 0.24
          case "rabato18" => 0.18
          case "rabato12" => 0.12
          case "rabato0" => 0
        }
      }
    }

    lazy val studento_? = form.getFieldValue(form.studento) == Some(true)

    lazy val studentaRabato: Double = {
      if(studento_?) Prezoj.studentaRabato  else 0
    }

    lazy val cxeesto: Set[String] = form.getFieldValue(form.cxeestoElekto).get.map{
      case (row, col) => row.id
    }.toSet

    lazy val noktoj: Int = {

      if (form.getFieldValue(form.cxeesto).exists(_.value == "cxiun")) {
        7
      } else {
        cxeesto("27/28").toInt + cxeesto("28/29").toInt + cxeesto("29/30").toInt + cxeesto("30/31").toInt + cxeesto("31/1").toInt + cxeesto("1/2").toInt + cxeesto("2/3").toInt
      }
    }

    lazy val programo: Map[String, Euroj] = {

      val naskiita = form.dates.str2millis(form.getFieldValue(form.naskigxdato).get)

      val agxKategorio = if(naskiita > form.dates.str2millis("1999-12-26")) 0
      else if(naskiita > form.dates.str2millis("19`4-12-26")) 1
      else 2


      val lando = form.getFieldValue(form.lando).get.value

      val aLandoj = Seq("ad", "at", "bh", "be", "gb", "dk", "fi", "fr", "de", "ie", "is", "il", "it", "jp", "ca", "qa", "kw", "li", "lu", "mc", "nl", "no", "om", "sm", "sa", "se", "ch", "ae", "us")

      val bLandoj = Seq(
        "al", "au", "ba", "bn", "bg", "cz", "ee", "gr", "es", "hu", "cy", "hr", "lt", "lv", "mk", "mt", "me", "nz", "pl", "pt", "ru", "rs", "sg", "sk", "si", "kr", "tw", "tr")

      val landoKategorio = if(aLandoj.contains(lando)) 0 else if (bLandoj.contains(lando)) 1 else 2


      val programo = Prezoj.programo(agxKategorio)(landoKategorio) * scala.math.min((1+noktoj),7).toDouble/7 * rabataMultiplikanto

      Map( s"Programkotizo" -> programo)
    }

    val mangxado: Map[String, Euroj] = {

      val matenmangxo: Boolean = form.getFieldValue(form.matenmangxoj).getOrElse(false)
      val tagmangxo: Boolean = form.getFieldValue(form.tagmangxoj).getOrElse(false)
      val vespermangxo: Boolean = form.getFieldValue(form.vespermangxoj).getOrElse(false)

      val kiomMatenmangxoj = noktoj
      val kiomTagmangxoj = noktoj - 1
      val kiomVespermangxoj = noktoj - cxeesto("31/1").toInt // silvestre ne estas vespermangxo

      val prezoMatenmangxo = Prezoj.matenmangxo * rabataMultiplikanto
      val prezoTagmangxo = Prezoj.vespermangxo * rabataMultiplikanto
      val prezoVespermangxo = Prezoj.vespermangxo * rabataMultiplikanto

      Map(s"Matenmangxoj (${kiomMatenmangxoj}x${prezoMatenmangxo})" -> kiomMatenmangxoj*prezoMatenmangxo,
        s"Tagmangxoj (${kiomTagmangxoj}x${prezoTagmangxo})" -> kiomTagmangxoj*prezoTagmangxo,
        s"Vespermangxoj (${kiomVespermangxoj}x${prezoVespermangxo})" -> kiomVespermangxoj*prezoVespermangxo)
    }

    val logxado: Map[String, Euroj] = {

      val logxelekto = form.getFieldValue(form.logxado).get.value

      val prezoKundusxejo = Prezoj.tranokto(2) * rabataMultiplikanto
      val prezoSendusxejo = Prezoj.tranokto(1) * rabataMultiplikanto
      val prezoAmasejo = Prezoj.tranokto(0) * rabataMultiplikanto

      val imposto: Euroj = if(studento_?) 0 else Prezoj.imposto

      (logxelekto match {
        case "2-lita-cxambro" => Map(
          s"Cxambro kun propra dusxejo (${noktoj}x${prezoKundusxejo})" -> noktoj * prezoKundusxejo,
          "Dulita krompago" ->  Prezoj.dulitaKrompago
        )
        case "4-5-lita-cxambro-dusxejo" => Map(
          "Cxambro kun propra dusxejo (${noktoj}x${prezoKundusxejo})" -> noktoj * prezoKundusxejo
        )
        case "4-5-lita-cxambro-sen-dusxejo" => Map(
          "Cxambro kun koridora dusxejo (${noktoj}x${prezoSendusxejo})" -> noktoj * prezoSendusxejo
        )
        case "memzorge" => Map("Memzorga logxado" -> 0)

        case _: String => Map(
          "Amasejo (${noktoj}x${prezoAmasejo})" -> noktoj * prezoAmasejo
        )
      }).updated("Imposto por hungara ", imposto).mapValues(_.asInstanceOf[Euroj])


    }

    Kotizo(mangxado ++ logxado ++ programo) //++ invitletero
  }



}
