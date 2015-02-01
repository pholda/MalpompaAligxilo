package pl.pej.malpompaaligxilo.dsl

import org.scalatest.FunSuite

import scala.util.Try

class ISO extends FunSuite {
  test("abc") {
    val code2pl = Map(
    "cy" -> "Cypr",
    "cz" -> "Czechy",
    "cr" -> "Kostaryka",
    "cu" -> "Kuba",
    "cv" -> "Republika Zielonego Przylądka",
    "ci" -> "Wybrzeże Kości Słoniowej",
    "ch" -> "Szwajcaria",
    "cm" -> "Kamerun",
    "cl" -> "Chile",
    "co" -> "Kolumbia",
    "cn" -> "Chińska Republika Ludowa",
    "ca" -> "Kanada",
    "cd" -> "Demokratyczna Republika Kongo",
    "cg" -> "Kongo",
    "cf" -> "Republika Środkowoafrykańska",
    "fj" -> "Fidżi",
    "np" -> "Nepal",
    "fi" -> "Finlandia",
    "sm" -> "San Marino",
    "so" -> "Somalia",
    "nz" -> "Nowa Zelandia",
    "sc" -> "Seszele",
    "sb" -> "Wyspy Salomona",
    "se" -> "Szwecja",
    "sd" -> "Sudan",
    "sg" -> "Singapur",
    "to" -> "Tonga",
    "sy" -> "Syria",
    "na" -> "Namibia",
    "ng" -> "Nigeria",
    "ne" -> "Niger",
    "fr" -> "Francja",
    "ni" -> "Nikaragua",
    "no" -> "Norwegia",
    "nl" -> "Holandia",
    "sv" -> "Salwador",
    "tg" -> "Togo",
    "ve" -> "Wenezuela",
    "mz" -> "Mozambik",
    "my" -> "Malezja",
    "mx" -> "Meksyk",
    "mr" -> "Mauretania",
    "mw" -> "Malawi",
    "mv" -> "Malediwy",
    "mu" -> "Mauritius",
    "mt" -> "Malta",
    "mk" -> "Macedonia",
    "mh" -> "Wyspy Marshalla",
    "mn" -> "Mongolia",
    "mm" -> "Birma",
    "ml" -> "Mali",
    "mc" -> "Monako",
    "vc" -> "Saint Vincent i Grenadyny",
    "ma" -> "Maroko",
    "ru" -> "Rosja",
    "mg" -> "Madagaskar",
    "me" -> "Czarnogóra",
    "md" -> "Mołdawia",
    "sl" -> "Sierra Leone",
    "az" -> "Azerbejdżan",
    "au" -> "Australia",
    "at" -> "Austria",
    "ar" -> "Argentyna",
    "ao" -> "Angola",
    "am" -> "Armenia",
    "al" -> "Albania",
    "ag" -> "Antigua i Barbuda",
    "af" -> "Afganistan",
    "ae" -> "Zjednoczone Emiraty Arabskie",
    "ad" -> "Andora",
    "ly" -> "Libia",
    "lr" -> "Liberia",
    "ls" -> "Lesotho",
    "lt" -> "Litwa",
    "lu" -> "Luksemburg",
    "lv" -> "Łotwa",
    "li" -> "Liechtenstein",
    "ye" -> "Jemen",
    "lk" -> "Sri Lanka",
    "sz" -> "Suazi",
    "la" -> "Laos",
    "lb" -> "Liban",
    "lc" -> "Saint Lucia",
    "zw" -> "Zimbabwe",
    "ir" -> "Iran",
    "nr" -> "Nauru",
    "us" -> "Stany Zjednoczone",
    "sn" -> "Senegal",
    "dz" -> "Algieria",
    "sk" -> "Słowacja",
    "pw" -> "Palau",
    "ro" -> "Rumunia",
    "de" -> "Niemcy",
    "dj" -> "Dżibuti",
    "dk" -> "Dania",
    "dm" -> "Dominika",
    "do" -> "Dominikana",
    "ke" -> "Kenia",
    "pl" -> "Polska",
    "kg" -> "Kirgistan",
    "sa" -> "Arabia Saudyjska",
    "ki" -> "Kiribati",
    "kh" -> "Kambodża",
    "tj" -> "Tadżykistan",
    "fm" -> "Mikronezja",
    "km" -> "Komory",
    "tm" -> "Turkmenistan",
    "tn" -> "Tunezja",
    "kn" -> "Saint Kitts i Nevis",
    "kp" -> "Korea Północna",
    "tr" -> "Turcja",
    "kr" -> "Korea Południowa",
    "tt" -> "Trynidad i Tobago",
    "kw" -> "Kuwejt",
    "tw" -> "Republika Chińska",
    "tz" -> "Tanzania",
    "kz" -> "Kazachstan",
    "vu" -> "Vanuatu",
    "ws" -> "Samoa",
    "sr" -> "Surinam",
    "jp" -> "Japonia",
    "jo" -> "Jordania",
    "jm" -> "Jamajka",
    "qa" -> "Katar",
    "bw" -> "Botswana",
    "bt" -> "Bhutan",
    "zm" -> "Zambia",
    "br" -> "Brazylia",
    "bs" -> "###",
    "bz" -> "Belize",
    "by" -> "Białoruś",
    "bf" -> "Burkina Faso",
    "bg" -> "Bułgaria",
    "bd" -> "Bangladesz",
    "be" -> "Belgia",
    "bb" -> "Barbados",
    "ba" -> "Bahamy",
    "bn" -> "Brunei",
    "bo" -> "Boliwia",
    "bj" -> "Benin",
    "th" -> "Tajlandia",
    "bh" -> "Bahrajn",
    "bi" -> "Burundi",
    "ie" -> "Irlandia",
    "id" -> "Indonezja",
    "in" -> "Indie",
    "il" -> "Izrael",
    "st" -> "Wyspy Świętego Tomasza i Książęca",
    "rw" -> "Rwanda",
    "it" -> "Włochy",
    "is" -> "Islandia",
    "rs" -> "Serbia",
    "iq" -> "Irak",
    "gd" -> "Grenada",
    "es" -> "Hiszpania",
    "er" -> "Erytrea",
    "et" -> "Etiopia",
    "td" -> "Czad",
    "tl" -> "Timor Wschodni",
    "ec" -> "Ekwador",
    "eg" -> "Egipt",
    "ee" -> "Estonia",
    "va" -> "Watykan",
    "vn" -> "Wietnam",
    "ua" -> "Ukraina",
    "ug" -> "Uganda",
    "hn" -> "Honduras",
    "ht" -> "Haiti",
    "hu" -> "Węgry",
    "pt" -> "Portugalia",
    "hr" -> "Chorwacja",
    "uz" -> "Uzbekistan",
    "uy" -> "Urugwaj",
    "py" -> "Paragwaj",
    "tv" -> "Tuvalu",
    "za" -> "Republika Południowej Afryki",
    "gm" -> "Gambia",
    "gn" -> "Gwinea",
    "ph" -> "Filipiny",
    "gh" -> "Ghana",
    "pk" -> "Pakistan",
    "ge" -> "Gruzja",
    "pe" -> "Peru",
    "pg" -> "Papua-Nowa Gwinea",
    "ga" -> "Gabon",
    "pa" -> "Panama",
    "gb" -> "Wielka Brytania",
    "gy" -> "Gujana",
    "si" -> "Słowenia",
    "om" -> "Oman",
    "gt" -> "Gwatemala",
    "gw" -> "Gwinea Bissau",
    "gq" -> "Gwinea Równikowa",
    "gr" -> "Grecja",
    "ss" -> "Sudan Południowy"
    )

    var s = "al, au, Bosnio, bn, bg, cz, ee, gr, es, hu, cy, hr, lv, lt, mk, mt, me, nz, pl, pt, ro, rs, sg, sk, si, kr, tw, tr kaj ua."

    code2pl.foreach{case (k, v) =>
      s=s.replace(k+",", v+",")
    }

    println(s)
  }

}
