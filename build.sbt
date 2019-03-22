name := "sustentate-scala"

version := "0.1"

scalaVersion := "2.12.8"

val akkaHttpVersion = "10.1.7"

libraryDependencies += "com.typesafe.akka" %% "akka-http" % akkaHttpVersion
libraryDependencies += "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion
libraryDependencies += "com.typesafe.akka" %% "akka-stream" % "2.5.19"

