package ar.com.sustentate.imaging.routes

import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.server.Route
import ar.com.sustentate.imaging.json.RecognitionProtocol
import ar.com.sustentate.imaging.models.RecognitionRequest

trait ImagingRoutes extends RecognitionProtocol {
  import akka.http.scaladsl.server.Directives._
  implicit def system: ActorSystem

  private lazy val log = Logging(system, classOf[ImagingRoutes])

  lazy val imagingRoutes: Route =
    pathPrefix("recognize") {
      put {
        entity(as[RecognitionRequest]) { request =>
          complete("Hello World")
        }
      }
    }
}
