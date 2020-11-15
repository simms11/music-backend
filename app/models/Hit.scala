package models

import play.api.libs.json.{Format, Json}

case class Hit(id: Int, name: String, yearReleased: Int, album: String, chartedUSA: Int, fact: String)

object Hit {
  implicit val format: Format[Hit] = Json.format[Hit]
}
