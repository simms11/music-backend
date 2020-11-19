package controllers

import famousHits.FamousHits
import javax.inject.{Inject, Singleton}
import models.Hit
import models.Hit.format
import play.api.data.Form
import play.api.libs.json.Json
import play.api.libs.json.Json._
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}
import play.modules.reactivemongo._
import reactivemongo.play.json._
import reactivemongo.play.json.collection.{JSONCollection, _}

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class HitsController @Inject()(controllerComponents: ControllerComponents,
                               val reactiveMongoApi: ReactiveMongoApi)
  extends AbstractController(controllerComponents) with MongoController with ReactiveMongoComponents {
  implicit def ec: ExecutionContext = controllerComponents.executionContext

  def getHits: Action[AnyContent] = Action {
    val hits = FamousHits.getHits
    Ok(toJson(hits))
  }

  def getHit(iD: Int): Action[AnyContent] = Action {
    val hit = FamousHits.getHits(iD)
    Ok(toJson(hit))
  }

  def deleteHit(id: String): Action[AnyContent] = Action.async {
    val query = Json.obj(
      "name" -> id,
    )
    collection.flatMap(_.delete.one(query)).map(lastError =>
      Ok("Mongo LastError: %s".format(lastError)))
  }

  def collection: Future[JSONCollection] =
    database.map(_.collection[JSONCollection]("hits"))


  def addHit() = Action.async { implicit request =>
    Hit.form
      .bindFromRequest()
      .fold(
        (formWithErrors: Form[Hit]) => Future.successful(BadRequest(s"Something went wrong => [$formWithErrors]")),
        hit => {
          collection.flatMap(_.insert.one(hit)).map(lastError =>
            Ok("Mongo LastError: %s".format(lastError)))
        })
  }
}


