import com.typesafe.sbt.packager.universal.UniversalKeys
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import play.twirl.sbt.SbtTwirl
import sbt.Keys._
import sbt._

object MalpompaAligxilo extends Build with UniversalKeys {

  val scalajsAngulateVersion = "0.2.2"

  val defaults = Defaults.coreDefaultSettings ++ List(
    organization := "pl.pholda.malpompaaligxilo",
    version := "0.1.5-SNAPSHOT",
    scalaVersion := "2.11.7",
    libraryDependencies ++= List(
      "com.lihaoyi" %%% "utest" % "0.3.1"

    ),
    testFrameworks += new TestFramework("utest.runner.Framework")
  )

  lazy val root = project.in(file(".")).settings(defaults:_*).settings(
    publishArtifact := false
  ).aggregate(coreJS, coreJVM, /*coreTests, */dslJS, dslJVM, googleAPI, templates/*, Examples.examples*/)

  lazy val core = crossProject.in(file("core")).settings(
    name := "core"
  ).settings(defaults:_*).jvmSettings(
    libraryDependencies ++= Seq(
      "joda-time" % "joda-time" % "2.7",
      "org.joda" % "joda-convert" % "1.7",
      "tv.cntt" %% "scaposer" % "1.5"
    )
  ).jsSettings(
    libraryDependencies ++= Seq(
      "biz.enef" %%% "scalajs-angulate" % scalajsAngulateVersion,
      "be.doeraene" %%% "scalajs-jquery" % "0.8.0"
    ),
    jsDependencies += RuntimeDOM
  )
  lazy val coreJVM = core.jvm
  lazy val coreJS = core.js
  //for intellij idea
  lazy val coreShared = Project("coreShared", file("core/shared")).settings(defaults:_*)

  lazy val dsl = crossProject.in(file("dsl")).settings(defaults:_*).settings(
    libraryDependencies ++= Seq(
      "org.scala-js" % "scala-parser-combinators_sjs0.6_2.11" % "1.0.2.1"
    )
  ).jsSettings(
    jsDependencies += RuntimeDOM
  ).dependsOn(core)

  lazy val dslJVM = dsl.jvm
  lazy val dslJS = dsl.js
  //for intellij idea
  lazy val dslShared = Project("dslShared", file("dsl/shared")).settings(defaults:_*).settings(
    libraryDependencies ++= Seq(
      "org.scala-js" % "scala-parser-combinators_sjs0.6_2.11" % "1.0.2"
  )).dependsOn(coreShared)

  lazy val googleAPI = Project(id = "googleAPI",
    base = file("googleAPI"),
    settings = defaults ++ List(
      name := "google-api",
      libraryDependencies ++= Seq(
        "com.google.gdata" % "core" % "1.47.1",
        "com.google.api-client" % "google-api-client" % "1.20.0",
        "com.typesafe" % "config" % "1.3.0" % "test"
      )
    )
  ).dependsOn(coreJVM)

  lazy val templates = project.in(file("templates")).settings(defaults:_*).settings(
    name := "templates"
  ).dependsOn(coreJVM).enablePlugins(SbtTwirl)

  val scalajsOutputDir = Def.settingKey[File]("directory for javascript files output by scalajs")
}
