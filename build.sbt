import com.typesafe.sbt.packager.docker._

name := "sustentate-scala"

lazy val commonSettings = Seq(
  organization := "ar.com.sustentate",
  version := "0.10",
  scalaVersion := "2.12.8"
)

enablePlugins(JavaAppPackaging)

dockerEntrypoint ++= Seq(
  """-DakkaActorSystemName="$AKKA_ACTOR_SYSTEM_NAME"""",
  """-Dakka.remote.netty.tcp.hostname="$(eval "echo $AKKA_REMOTING_BIND_HOST")"""",
  """-Dakka.remote.netty.tcp.port="$AKKA_REMOTING_BIND_PORT"""",
  """$(IFS=','; I=0; for NODE in $AKKA_SEED_NODES; do echo "-Dakka.cluster.seed-nodes.$I=akka.tcp://$AKKA_ACTOR_SYSTEM_NAME@$NODE"; I=$(expr $I + 1); done)""",
  "-Dakka.io.dns.resolver=async-dns",
  "-Dakka.io.dns.async-dns.resolve-srv=true",
  "-Dakka.io.dns.async-dns.resolv-conf=on",
  """-DhttpHost="$HTTP_HOST"""",
  """-DhttpPort="$HTTP_PORT"""",
  """-DclusterMembershipAskTimeout="$CLUSTER_MEMBERSHIP_ASK_TIMEOUT""""
)
dockerCommands :=
  dockerCommands.value.flatMap {
    case ExecCmd("ENTRYPOINT", args @ _*) => Seq(Cmd("ENTRYPOINT", args.mkString(" ")))
    case v => Seq(v)
  }

dockerRepository := Some("pacdaemon")
dockerUpdateLatest := true
dockerBaseImage := "local/openjdk-jre-8-bash"

lazy val root = (project in file("."))
  .aggregate(common, server, imaging)

lazy val common = (project in file("common"))
  .settings(commonSettings: _*)

lazy val imaging = (project in file("imaging"))
  .settings(commonSettings: _*)
  .dependsOn(common)

lazy val server = (project in file("server"))
  .settings(commonSettings: _*)
  .dependsOn(common)
  .dependsOn(imaging)