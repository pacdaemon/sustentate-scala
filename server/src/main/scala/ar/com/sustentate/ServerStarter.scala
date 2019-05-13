package ar.com.sustentate

import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.Http
import akka.http.scaladsl.Http.ServerBinding
import akka.stream.ActorMaterializer
import ar.com.sustentate.routes.{ClusterRoutes, SystemRoutes}
import ar.com.sustentate.imaging.routes.ImagingRoutes

import scala.concurrent.Future
import akka.http.scaladsl.server.Directives._

object ServerStarter
  extends App
  with SystemRoutes
  with ClusterRoutes
  with ImagingRoutes {

  implicit val system = ActorSystem("sustentate-system")
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher
  private lazy val log = Logging(system, ServerStarter.getClass)

  /*val httpHost =
    sys.props.getOrElse(
      "httpHost",
      throw new IllegalArgumentException("HTTP bind host must be defined by the httpHost property"))
*/
  val(host, port) = ("0.0.0.0", 8080)
  val bindingFuture: Future[ServerBinding] = Http().bindAndHandle(systemRoutes ~ clusterRoutes ~ imagingRoutes, host, port)

  log.info(s"Server online at http://$host:$port/\n- Press Return to stop...")
  bindingFuture.failed.foreach { ex =>
    log.error(ex, s"Failed to bind to $host:$port!")
  }
}
