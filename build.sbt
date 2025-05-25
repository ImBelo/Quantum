ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.4.0"
Test / javaOptions ++= Seq(
  "--add-opens=java.base/jdk.internal.misc=ALL-UNNAMED",
  "-Dio.netty.tryReflectionSetAccessible=true"
)
lazy val root = (project in file("."))
  .settings(
    name := "Quantum"
  )
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.19" % Test
