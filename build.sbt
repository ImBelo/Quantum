ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "3.4.0"

lazy val root = (project in file("."))
  .settings(
    name := "Quantum",
    // Recommended compiler options for Scala 3
    scalacOptions ++= Seq(
      "-deprecation",
      "-feature",
      "-unchecked"
    )
  )
libraryDependencies ++= Seq(
  // Core numerical computing
  "org.scalanlp" %% "breeze" % "2.1.0",

  // Optional visualization (LGPL licensed)
  "org.scalanlp" %% "breeze-viz" % "2.1.0",
  "org.scalanlp" %% "breeze" % "2.1.0",
)
