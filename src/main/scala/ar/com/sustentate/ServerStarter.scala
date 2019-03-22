package ar.com.sustentate

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.Http.ServerBinding
import akka.stream.ActorMaterializer
import ar.com.sustentate.routes.EchoRoutes

import scala.concurrent.Future

object ServerStarter
  extends App
  with EchoRoutes {

  implicit val system = ActorSystem("sustentate-system")
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  val(host, port) = ("localhost", 8080)
  val bindingFuture: Future[ServerBinding] = Http().bindAndHandle(echoRoutes, host, port)

  log.info(s"Server online at http://$host:$port/\n- Press Return to stop...")
  bindingFuture.failed.foreach { ex =>
    log.error(ex, s"Failed to bind to $host:$port!")
  }
}
