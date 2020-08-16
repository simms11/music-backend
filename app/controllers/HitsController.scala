package controllers
import javax.inject.{Inject, Singleton}
import models.Hits
import play.api.libs.json.Json._
import play.api.mvc.{BaseController, ControllerComponents, InjectedController}
import famousHits.FamousHits

@Singleton
class HitsController @Inject()(val controllerComponents: ControllerComponents, famousHits: FamousHits) extends BaseController{
  def getHits() = Action {
    val hits = famousHits.getHits
    Ok(views.html.hits(stringify(toJson(hits))))
  }

  def getHit(iD:Int) = Action {
    val hit = famousHits.getHits(iD)
    Ok(views.html.hits(stringify(toJson(hit))))
  }

  def addHit() = Action(parse.json) { implicit request =>
    val newHit = request.body.as[Hits]
    val hit = famousHits.addHit(newHit)
    Ok(views.html.hits(stringify(toJson(hit))))
  }
}
