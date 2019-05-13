package ar.com.sustentate.imaging.routes

import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.FileUploadDirectives.fileUpload
import akka.http.scaladsl.server.directives.MethodDirectives.put
import akka.http.scaladsl.server.directives.PathDirectives.pathPrefix
import akka.http.scaladsl.server.directives.RouteDirectives.complete

trait ImagingRoutes {
  implicit def system: ActorSystem

  private lazy val log = Logging(system, classOf[ImagingRoutes])

  lazy val imagingRoutes: Route =
    pathPrefix("recognize") {
      put {
        fileUpload("image") {
          case (metaData, byteSource) => {
            complete("Hello World")
          }
        }
      }
    }
}
