package models

import play.api.data.Form
import play.api.data.Forms.{number, text}
import play.api.data.validation.Constraints
import play.api.data.validation.Constraints.nonEmpty
import play.api.data.Forms._
import play.api.libs.json.{Json, OFormat}

case class Hit(id: Int, name: String, yearReleased: Int, album: String, chartedUSA: Int, fact: String)

object Hit {
  implicit val format: OFormat[Hit] = Json.format[Hit]

  val form: Form[Hit] = Form(
    mapping(
    "id" -> number.verifying(Constraints.min(0)),
    "name" -> text.verifying(nonEmpty),
    "yearReleased" -> number.verifying(Constraints.min(0)),
    "album" -> text.verifying(nonEmpty),
    "chartedUSA" -> number.verifying(Constraints.min(0)),
    "fact" -> text.verifying(nonEmpty)
  )(Hit.apply)(Hit.unapply))

}
