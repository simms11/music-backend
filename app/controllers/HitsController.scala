package controllers

import javax.inject.{Inject, Singleton}
import models.Hit
import models.Hit.format
import play.api.data.Form
import play.api.libs.json.Json
import play.api.libs.json.Json._
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}
import play.modules.reactivemongo._
import reactivemongo.api.{Cursor, ReadPreference}
import reactivemongo.play.json._
import reactivemongo.play.json.collection.{JSONCollection, _}

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class HitsController @Inject()(controllerComponents: ControllerComponents,
                               val reactiveMongoApi: ReactiveMongoApi)
  extends AbstractController(controllerComponents) with MongoController with ReactiveMongoComponents {
  implicit def ec: ExecutionContext = controllerComponents.executionContext

  def getHits: Action[AnyContent] = Action.async {
    collection.flatMap(
      _.find(Json.obj())
        .cursor[Hit](ReadPreference.primary)
        .collect[Seq](Int.MaxValue, Cursor.FailOnError[Seq[Hit]]()))
      .map(hits => Ok(Json.toJson(hits)))
  }


  def getHit(name:String): Action[AnyContent] = Action.async{
    val query = Json.obj(
     // "id" -> id,
      "name" -> name
    )

    collection.flatMap(_.find(query).one[Hit]).map {
      case Some(hit) => Ok(Json.toJson(hit))
      case _ => NotFound("Hit not found")
    }

  }

  def updateHit(name:String) : Action[AnyContent]  = Action.async{  implicit request =>

    val query = Json.obj(
      "name" -> name
    )

    collection.flatMap(_.update.one(query,name, upsert = true))
      .map (lastError =>
        Ok("Mongo LastError: %s".format(lastError)))

  }

  def deleteHit(name:String): Action[AnyContent] = Action.async {
    val query = Json.obj(
      "name" -> name
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


