import sbt.Compile

// Build-wide settings
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "3.4.0"



lazy val root = (project in file("."))
  .settings(
    name := "Quantum",

    // Module-specific compiler options

    // Compilation control

    // Dependency optimizations

  )
libraryDependencies ++= Seq(
  "org.scalanlp" %% "breeze" % "2.1.0",
  "org.scalanlp" %% "breeze-viz" % "2.1.0",
  "org.scalanlp" %% "breeze-natives" % "2.1.0", // Added native version for better performance
  "org.scalatest" %% "scalatest" % "3.2.19" % "test"
)
