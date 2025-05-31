import sbt.Compile

// Build-wide settings
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "3.4.0"

// Performance optimizations
ThisBuild / fork := true  // Run in separate JVM
ThisBuild / turbo := true  // Enable turbo mode for faster builds (sbt 1.4+)
ThisBuild / useSuperShell := false  // Disable fancy shell for better performance in CI

// Memory settings (adjust based on your system)
ThisBuild / javaOptions ++= Seq(
  "-Xmx4G",  // Increased heap size
  "-XX:+UseG1GC",  // Better garbage collector
  "-XX:ReservedCodeCacheSize=512m"  // Larger code cache
)

// Compiler options
ThisBuild / scalacOptions ++= Seq(
  "-deprecation",
  "-feature",
  "-unchecked",
)

lazy val root = (project in file("."))
  .settings(
    name := "Quantum",

    // Module-specific compiler options
    scalacOptions ++= Seq(
      "-Yskip-recovery",
      "-Yno-adapted-args",
      "-Xdisable-assertions",
      "-explain"  // Better error explanations without full stack traces
    ),

    // Compilation control
    Compile / compile / maxErrors := 10,  // Reduced from 20 to fail faster
    Compile / compileOrder := CompileOrder.ScalaThenJava,  // Changed to ScalaThenJava for better ordering
    Compile / doc / scalacOptions ++= Seq("-no-link-warnings"),

    // Dependency optimizations
    libraryDependencies ++= Seq(
      "org.scalanlp" %% "breeze" % "2.1.0",
      "org.scalanlp" %% "breeze-viz" % "2.1.0",
      "org.scalanlp" %% "breeze-natives" % "2.1.0"  // Added native version for better performance
    )

  )
