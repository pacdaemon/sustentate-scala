import sbt._

object Versions {
  val akkaHttpVersion = "10.1.7"
  val akkaVersion = "2.5.23"
}

object Libraries {

  import Versions._

  val akkaActor = "com.typesafe.akka" %% "akka-actor" % akkaVersion
  val akkaCluster = "com.typesafe.akka" %% "akka-cluster" % akkaVersion
  val akkaClusterSharding =  "com.typesafe.akka" %% "akka-cluster-sharding" % akkaVersion
  val akkaHttp = "com.typesafe.akka" %% "akka-http" % akkaHttpVersion
  val akkaHttpPlayJson = "de.heikoseeberger" %% "akka-http-play-json" % "1.19.0-M2"
  val akkaHttpSprayJson = "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion
  val akkaPersistenceCouchBase = "com.lightbend.akka" %% "akka-persistence-couchbase" % "1.0"
  val akkaStream = "com.typesafe.akka" %% "akka-stream" % akkaVersion
  val playJson = "com.typesafe.play" %% "play-json" % "2.6.5"

  // Testing
  val akkaHttpTestKit = "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % Test
  val akkaStreamTestkit = "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion % Test
  val akkaTestkit = "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test
  val scalactic = "org.scalactic" %% "scalactic" % "3.0.5"
  val scalaMock = "org.scalamock" %% "scalamock" % "4.1.0" % Test
  val scalaTest = "org.scalatest" %% "scalatest" % "3.0.5" % Test
}

object Dependencies {

  import Libraries._

  val commonDependencies = Seq(
    scalactic,
    scalaMock,
    scalaTest
  )

  val akkaDependencies = Seq(
    akkaActor,
    akkaCluster,
    akkaClusterSharding,
    akkaHttp,
    akkaHttpSprayJson,
    akkaPersistenceCouchBase,
    akkaStream,
    akkaHttpTestKit,
    akkaStreamTestkit,
    akkaTestkit
  )

  val playDependencies = Seq(
    playJson
  )
}