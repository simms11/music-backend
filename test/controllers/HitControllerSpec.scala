package controllers

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 *
 * For more information, see https://www.playframework.com/documentation/latest/ScalaTestingWithScalaTest
 */
//class HitControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {
//  val controller = new HitsController(Helpers.stubControllerComponents())
//  implicit val sys = ActorSystem("MyTest")
//
//  "Hits Controller GET" should {
//
//    "Get the hits from the controller" in {
//
//      val hit = controller.getHits().apply(FakeRequest(GET, "/hits"))
//
//      status(hit) mustBe OK
//      contentType(hit) mustBe Some("application/json")
//
//    }
//
//    "get a row of data from the application" in {
//      val hit = controller.getHit(2).apply(FakeRequest(GET, "/hits/:id "))
//      val expectedJson = s"""{"id":3,"name":"All of the Lights","yearReleased":2010,"album":"My Beautiful Dark Twisted Fantasy","chartedUSA":18,"fact":"Drake also recorded vocals for this track but was dropped from the final version."}"""
//
//      status(hit) mustBe OK
//      contentType(hit) mustBe Some("application/json")
//      contentAsString(hit) mustBe (expectedJson)
//
//    }
//
//    //    "Post data to the application" in {
//    //      val postHit = s"""{"id":7,"name":"The Test","yearReleased":2020,"album":"My Beautiful Test","chartedUSA":18,"fact":"Test"}"""
//    //
//    //      val hit = controller.addHit().apply(FakeRequest(POST, "/hits"))
//    //
//    //
//    //      status(hit) mustBe OK
//    //      contentType(hit) mustBe Some("application/json")
//    //
//    //
//    //
//    //    }
//    //
//
//  }
//
//
//}
