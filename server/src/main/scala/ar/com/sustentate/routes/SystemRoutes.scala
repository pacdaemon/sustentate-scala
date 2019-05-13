package ar.com.sustentate.routes

import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.MethodDirectives.get
import akka.http.scaladsl.server.directives.PathDirectives.pathPrefix
import akka.http.scaladsl.server.directives.RouteDirectives.complete

trait SystemRoutes {
 implicit def system: ActorSystem

 private lazy val log = Logging(system, classOf[SystemRoutes])

 lazy val systemRoutes: Route =
  pathPrefix("echo") {
   get {
    complete("echo")
   }
  }
}
