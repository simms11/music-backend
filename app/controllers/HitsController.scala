package controllers
import javax.inject.{Inject, Singleton}
import models.Hit
import play.api.libs.json.Json._
import play.api.mvc.{BaseController, ControllerComponents, InjectedController}
import famousHits.FamousHits

@Singleton
class HitsController @Inject()(val controllerComponents: ControllerComponents) extends BaseController{
  def getHits() = Action {
    val hits = FamousHits.getHits
    Ok(stringify(toJson(hits)))
  }

  def getHit(iD:Int) = Action {
    val hit = FamousHits.getHits(iD)
    Ok(stringify(toJson(hit)))
  }

  def addHit() = Action(parse.json) { implicit request =>
    val newHit = request.body.as[Hit]
    val hit = FamousHits.addHit(newHit)
    Ok(stringify(toJson(hit)))
  }
}
