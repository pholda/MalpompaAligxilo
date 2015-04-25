import play.twirl.sbt.SbtTwirl
import sbt.Keys._
import sbt._
import org.scalajs.sbtplugin.ScalaJSPlugin
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import com.typesafe.sbt.packager.universal.UniversalKeys

object MalpompaAligxilo extends Build with UniversalKeys {

  val defaults = Defaults.coreDefaultSettings ++ List(
    organization := "pl.pej.malpompaaligxilo",
    version := "0.1.2",
    scalaVersion := "2.11.5",
    libraryDependencies ++= List(
//      "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"
      "org.monifu" %% "minitest" % "0.11" % "test"//,
//      "org.monifu" %%% "minitest" % "0.11" % "test"
    ),

    testFrameworks += new TestFramework("minitest.runner.Framework")
  )

  lazy val root = Project(id = "malpompaAligxilo",
    base = file("."),
    settings = defaults ++ List(
      publishArtifact := false
    )
  ).aggregate(core, coreTests, googleAPI, twirlTemplates, Examples.examples)

  lazy val core = Project(id = "core",
    base = file("core"),
    settings = defaults ++ List(
      name := "core",
      libraryDependencies ++= Seq(
        "org.scala-lang.modules" % "scala-parser-combinators_2.11" % "1.0.3",
        "joda-time" % "joda-time" % "2.0",
        "tv.cntt" %% "scaposer" % "1.5"
      )
    )
  ).enablePlugins(ScalaJSPlugin)

  lazy val coreTests = Project(id = "coreTests",
    base = file("coreTests"),
    settings = defaults ++ List(
      name := "coreTests",
      publishArtifact := false
    )
  ).dependsOn(core)

  lazy val googleAPI = Project(id = "googleAPI",
    base = file("googleAPI"),
    settings = defaults ++ List(
      name := "google-api",
      libraryDependencies ++= Seq(
        "com.google.gdata" % "core" % "1.47.1",
        "com.google.api-client" % "google-api-client" % "1.19.1"
      )
    )
  ).dependsOn(core)

  lazy val twirlTemplates = Project(id = "twirlTemplates",
    base = file("twirlTemplates"),
    settings = defaults ++ List(
      name := "twirl-templates"/*,
      publishArtifact := false*/
    )
  ).dependsOn(core).enablePlugins(SbtTwirl)

  val scalajsOutputDir = Def.settingKey[File]("directory for javascript files output by scalajs")
}
