package ar.com.sustentate.routes

import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.{Matchers, WordSpec}

class SystemRoutesTest
  extends WordSpec
  with Matchers
  with ScalatestRouteTest
  with SystemRoutes {

  "The service" should {
    "respond to echo" in {
      Get("/echo") ~> systemRoutes ~> check {
        responseAs[String] shouldEqual "echo"
      }
    }
  }

}
