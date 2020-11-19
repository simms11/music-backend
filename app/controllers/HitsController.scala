package controllers

import famousHits.FamousHits
import javax.inject.{Inject, Singleton}
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

  def addHit() = Action.async {
    val json = Json.obj(
      "name" -> "testUser",
      "age" -> "30",
      "created" -> new java.util.Date().getTime()
    )
    // collection.
    collection.flatMap(_.insert.one(json)).map(_ => Ok(json))
  }

  def collection: Future[JSONCollection] =
    database.map(_.collection[JSONCollection]("hits"))
}


