import Dependencies._

name := "sustentate-scala"

lazy val commonSettings = Seq(
  organization := "ar.com.sustentate",
  version := "0.12",
  scalaVersion := "2.12.8"
)

lazy val root = (project in file("."))
  .aggregate(common, server, imaging)

lazy val common = (project in file("common"))
  .settings(commonSettings: _*)
  .settings(name := "sustentate-common",
    libraryDependencies ++= commonDependencies
  )

lazy val imaging = (project in file("imaging"))
  .settings(commonSettings: _*)
  .settings(name := "sustentate-imaging",
    libraryDependencies ++= commonDependencies,
    libraryDependencies ++= akkaDependencies,
    libraryDependencies ++= playDependencies)
  .dependsOn(common)

lazy val server = (project in file("server"))
  .settings(commonSettings: _*)
  .settings(name := "sustentate-server",
    libraryDependencies ++= commonDependencies,
    libraryDependencies ++= akkaDependencies,
    libraryDependencies ++= playDependencies)
  .dependsOn(common, imaging)
