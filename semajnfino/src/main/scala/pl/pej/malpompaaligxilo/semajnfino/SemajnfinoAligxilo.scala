package pl.pej.malpompaaligxilo.semajnfino

import pl.pej.malpompaligxilo.form._
import pl.pej.malpompaligxilo.form.field.{EnumOption, SelectField, EmailField, StringField}
import pl.pej.malpompaligxilo.util.I18nString
import pl.pej.util.Dates

class SemajnfinoAligxilo(val dates: Dates, val _getRawFieldValue: FieldName => Seq[String]) extends Form {
  override def id: String = "semajnfinoAligxilo"

  override protected def getRawFieldValue(fieldName: FieldName): Seq[String] = _getRawFieldValue(fieldName)

  override def fields: List[Field[_]] = personaNomo :: familiaNomo :: retposxtadreso :: telefonnumero :: urbo ::
    alveno :: esperanto :: komentoj :: Nil

  val personaNomo = Field(
    name = "personaNomo",
    caption = I18nString(
      "eo" -> "Persona Nomo",
      "pl" -> "Imię"
    ),
    required = true,
    `type` = StringField()
  )

  val familiaNomo = Field(
    name = "familiaNomo",
    caption = I18nString(
      "eo" -> "Familia Nomo",
      "pl" -> "Nazwisko"
    ),
    required = true,
    `type` = StringField()
  )
  val retposxtadreso = Field(
    name = "retposxtadreso",
    caption = I18nString(
      "eo" -> "Retpoŝtadreso",
      "pl" -> "Adres email"
    ),
    required = true,
    `type` = EmailField
  )
  val telefonnumero = Field(
    name = "telefonnumero",
    caption = I18nString(
      "eo" -> "Telefonnumero",
      "pl" -> "Telefon"
    ),
    description = Some(I18nString("eo" -> "Por povi kontaktiĝi", "pl" -> "W celu ewentualnego kontaktu")),
    `type` = StringField()
  )
  val urbo = Field(
    name = "urbo",
    caption = I18nString(
      "eo" -> "Urbo",
      "pl" -> "Miasto"
    ),
    `type` = StringField()
  )
  val alveno = Field(
    name = "alveno",
    caption = I18nString(
      "eo" -> "Kiel kaj kiam vi alvenos?",
      "pl" -> "Kiedy i jak zamierzasz przyjechać?"
    ),
    `type` = StringField(textarea = true)
  )
  val esperanto = Field(
    name = "esperanto",
    caption = I18nString(
      "eo" -> "Nivelo de esperanto",
      "pl" -> "Poziom Esperanto"
    ),
    `type` = SelectField(List(
      EnumOption("novulo", I18nString("eo" -> "novulo", "pl" -> "nowy")),
      EnumOption("komencanto", I18nString("eo" -> "komencanto", "pl" -> "początkujący")),
      EnumOption("spertulo", I18nString("eo" -> "spertulo", "pl" -> "doświadczony")),
      EnumOption("specialisto", I18nString("eo" -> "specialisto", "pl" -> "specjalista"))
    ))
  )
  val komentoj = Field(
    name = "komentoj",
    caption = I18nString(
      "eo" -> "Komentoj",
      "pl" -> "Komentarze"
    ),
    description = Some(I18nString(
      "eo" -> "Io ajn, ekz. programero kiun vi ŝatus okazigi",
      "pl" -> "Cokolwiek, np. program który byś chciał poprowadzić (krótka prezentacja itp.)"
    )),
    `type` = StringField(textarea = true)
  )
}