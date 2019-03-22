package ar.com.sustentate.routes

import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.MethodDirectives._
import akka.http.scaladsl.server.directives.PathDirectives._
import akka.http.scaladsl.server.directives.RouteDirectives._

trait EchoRoutes {
 implicit def system: ActorSystem

 lazy val log = Logging(system, classOf[EchoRoutes])

 lazy val echoRoutes: Route =
  pathPrefix("echo") {
   get {
    complete("Hello World")
   }
  }
}
