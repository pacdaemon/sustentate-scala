package ar.com.sustentate

import akka.cluster.{Member, MemberStatus}
import com.lightbend.example.cluster.ClusterMembership
import play.api.libs.json.{Format, JsResult, JsString, JsValue, Json}

object JsonSerializer {
  implicit val memberStatusJsonSerializer: Format[MemberStatus] = new Format[MemberStatus] {
    override def reads(json: JsValue): JsResult[MemberStatus] =
      throw new UnsupportedOperationException("Reading MemberStatus from json is not supported")

    override def writes(o: MemberStatus): JsValue =
      JsString(
        o match {
          case MemberStatus.Joining  => "Joining"
          case MemberStatus.WeaklyUp => "WeaklyUp"
          case MemberStatus.Up       => "Up"
          case MemberStatus.Down     => "Down"
          case MemberStatus.Exiting  => "Exiting"
          case MemberStatus.Leaving  => "Leaving"
          case MemberStatus.Removed  => "Removed"
        })
  }

  implicit val memberJsonSerializer: Format[Member] = new Format[Member] {
    override def reads(json: JsValue): JsResult[Member] =
      throw new UnsupportedOperationException("Reading Member from json is not supported")

    override def writes(o: Member): JsValue =
      Json.obj(
        "address" -> o.uniqueAddress.address.toString,
        "status" -> o.status,
        "roles" -> o.roles)
  }

  implicit val membershipInfoJsonSerializer: Format[ClusterMembership.MembershipInfo] = Json.format
}
