import play.twirl.sbt.SbtTwirl
import sbt.Keys._
import sbt._
import org.scalajs.sbtplugin.ScalaJSPlugin
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import com.typesafe.sbt.packager.universal.UniversalKeys

object MalpompaAligxilo extends Build with UniversalKeys {

  val defaults = Defaults.coreDefaultSettings ++ List(
    organization := "pl.pej.malpompaaligxilo",
    version := "0.1.3-SNAPSHOT",
    scalaVersion := "2.11.5",
    libraryDependencies ++= List(
      "com.lihaoyi" %% "utest" % "0.3.1"

    ),
    testFrameworks += new TestFramework("utest.runner.Framework")
  )

  lazy val root = project.in(file(".")).settings(defaults:_*).settings(
    publishArtifact := false
  ).aggregate(coreJS, coreJVM, /*coreTests, */googleAPI, twirlTemplates/*, Examples.examples*/)

  lazy val core = crossProject.in(file("core")).settings(
    name := "core"//,
//    defaults:_*
  ).settings(defaults:_*).jvmSettings(
    libraryDependencies ++= Seq(
      "org.scala-lang.modules" % "scala-parser-combinators_2.11" % "1.0.3",
      "joda-time" % "joda-time" % "2.7",
      "org.joda" % "joda-convert" % "1.7",
      "tv.cntt" %% "scaposer" % "1.5"
    )
  ).jsSettings(

  )
  lazy val coreJVM = core.jvm
  lazy val coreJS = core.js
  //for intellij idea
  lazy val coreShared = Project("coreShared", file("core/shared")).settings(defaults:_*)

//  lazy val coreTests = Project(id = "coreTests",
//    base = file("coreTests"),
//    settings = defaults ++ List(
//      name := "coreTests",
//      publishArtifact := false
//    )
//  ).dependsOn(coreJVM)
//
  lazy val googleAPI = Project(id = "googleAPI",
    base = file("googleAPI"),
    settings = defaults ++ List(
      name := "google-api",
      libraryDependencies ++= Seq(
        "com.google.gdata" % "core" % "1.47.1",
        "com.google.api-client" % "google-api-client" % "1.19.1"
      )
    )
  ).dependsOn(coreJVM)

  lazy val twirlTemplates = Project(id = "twirlTemplates",
    base = file("twirlTemplates"),
    settings = defaults ++ List(
      name := "twirl-templates"/*,
      publishArtifact := false*/
    )
  ).dependsOn(coreJVM).enablePlugins(SbtTwirl)

  val scalajsOutputDir = Def.settingKey[File]("directory for javascript files output by scalajs")
}
