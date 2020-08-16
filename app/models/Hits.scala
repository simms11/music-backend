package models
import play.api.libs.json.{JsPath, Reads, Writes}
import play.api.libs.functional.syntax._

case class Hits(id:Int, name:String, yearReleased:Int, album:String, chartedUSA:Int, fact:String)

object Hits {
  implicit val reads : Reads[Hits] = (
    (JsPath \ "id").read[Int] and
    (JsPath \ "name").read[String] and
    (JsPath \ "yearReleased").read[Int] and
    (JsPath \ "album").read[String] and
    (JsPath \ "chartedUSA").read[Int] and
    (JsPath \ "fact").read[String]
    )(Hits.apply _)

  implicit val writes : Writes[Hits] = (
    (JsPath \ "id").write[Int] and
      (JsPath \ "name").write[String] and
      (JsPath \ "yearReleased").write[Int] and
      (JsPath \ "album").write[String] and
      (JsPath \ "chartedUSA").write[Int] and
      (JsPath \ "fact").write[String]
    )(unlift(Hits.unapply _))
}
