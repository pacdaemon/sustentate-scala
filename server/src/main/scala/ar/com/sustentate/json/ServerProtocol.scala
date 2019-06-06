package ar.com.sustentate.json

import akka.cluster.{Member, MemberStatus}
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.lightbend.example.cluster.ClusterMembership
import spray.json.{DefaultJsonProtocol, JsArray, JsObject, JsString, JsValue, JsonFormat, RootJsonFormat}

trait ServerProtocol extends SprayJsonSupport with DefaultJsonProtocol {

  implicit object MemberStatusFormatter extends RootJsonFormat[MemberStatus] {
    def write(s: MemberStatus) =
      JsString(
        s match {
          case MemberStatus.Joining => "Joining"
          case MemberStatus.WeaklyUp => "WeaklyUp"
          case MemberStatus.Up => "Up"
          case MemberStatus.Down => "Down"
          case MemberStatus.Exiting => "Exiting"
          case MemberStatus.Leaving => "Leaving"
          case MemberStatus.Removed => "Removed"
        })

    def read(value: JsValue) =
      throw new UnsupportedOperationException("Reading MemberStatus from json is not supported")

  }

  implicit object MemberFormatter extends RootJsonFormat[Member] {

    def read(json: JsValue) =
      throw new UnsupportedOperationException("Reading Member from json is not supported")

    def write(o: Member): JsValue = {

      JsObject(
        "address" -> JsString(o.uniqueAddress.address.toString),
        "status" -> MemberStatusFormatter.write(o.status),
        "roles" -> JsArray(o.roles.map(JsString.apply).toSeq: _*)
      )
    }
  }

  implicit val membershipInfoJsonSerializer
      = jsonFormat1(ClusterMembership.MembershipInfo.apply)

}
