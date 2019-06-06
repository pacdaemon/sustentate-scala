package ar.com.sustentate.routes

import java.util.concurrent.TimeUnit

import akka.actor.ActorSystem
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.MethodDirectives.get
import akka.http.scaladsl.server.directives.PathDirectives.{path, pathEndOrSingleSlash}
import akka.http.scaladsl.server.directives.RouteDirectives.complete
import akka.pattern.AskSupport
import ar.com.sustentate.json.ServerProtocol
import com.lightbend.example.cluster.ClusterMembership

import scala.concurrent.duration.FiniteDuration

trait ClusterRoutes
  extends AskSupport
    with ServerProtocol {
  implicit def system: ActorSystem

  lazy val clusterMembership = system.actorOf(ClusterMembership.props, ClusterMembership.Name)

  //TODO: Centralize Timeout info

  lazy val clusterRoutes: Route =
    path("members") {
      pathEndOrSingleSlash {
        get {
          complete {
            // TODO: remove the line above

            //implicit val jsonSerializer = JsonSerializer.membershipInfoJsonSerializer
            clusterMembership.ask(ClusterMembership.GetMembershipInfo)(FiniteDuration(5000, TimeUnit.MICROSECONDS))
              .mapTo[ClusterMembership.MembershipInfo]
          }
        }
      }
    }
}
