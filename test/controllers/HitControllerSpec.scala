package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test.Helpers._
import play.api.test._

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 *
 * For more information, see https://www.playframework.com/documentation/latest/ScalaTestingWithScalaTest
 */
class HitControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {

  "HomeController GET" should {

    "render the index page from a new instance of controller" in {
      val controller = new HitsController(stubControllerComponents())
      val hits = controller.getHits().apply(FakeRequest(GET, "/hits"))

      status(hits) mustBe OK
      contentType(hits) mustBe Some("text/html")
      contentAsString(hits) must include ("Welcome to Play")
    }

    "render the index page from the application" in {
      val controller = inject[HitsController]
      val hits = controller.getHits().apply(FakeRequest(GET, "/hits"))

      status(hits) mustBe OK
      contentType(hits) mustBe Some("text/html")
      contentAsString(hits) must include ("Welcome to Play")
    }

    "render the index page from the router" in {
      val request = FakeRequest(GET, "/hits")
      val home = route(app, request).get

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include ("Welcome to Play")
    }
  }


}
