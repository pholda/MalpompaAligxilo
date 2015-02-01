import sbt._
import Keys._
import scalajs.sbtplugin.ScalaJSPlugin._

object MalpompaAligxilo extends Build {
  val defaults = Defaults.coreDefaultSettings ++ List(
    organization := "pl.pej.malpompaaligxilo",
    version := "0.1-SNAPSHOT",
    scalaVersion := "2.11.1",
    libraryDependencies ++= List(
      "org.scalatest" % "scalatest_2.11" % "2.2.3" % "test"
    )
  )

  lazy val root = Project(id = "malpompaAligxilo",
    base = file("."),
    settings = defaults ++ List(
//      name := "malpompaaligxilo",
      publishArtifact := false
    )
  ).aggregate(core, scalaJS, googleAPI, examples)

  lazy val core = Project(id = "core",
    base = file("core"),
    settings = defaults /*++ scalaJSSettings */++ List(
      name := "core",
      libraryDependencies ++= Seq(
        "org.scala-lang.modules" % "scala-parser-combinators_2.11" % "1.0.3",
//        "org.scala-lang.modules.scalajs" %%% "scalajs-jquery" % "0.6",
        "joda-time" % "joda-time" % "2.0"//,
//        "com.github.nscala-time" %% "nscala-time" % "1.6.0"
      )//,
//      skip in ScalaJSKeys.packageJSDependencies := false
    )
  )

  lazy val scalaJS = Project(id = "scalajs",
    base = file("scalajs"),
    settings = defaults ++ scalaJSSettings ++ List(
      name := "scalajs",
      libraryDependencies ++= Seq(
        "org.scala-lang.modules.scalajs" %%% "scalajs-jquery" % "0.6"
      ),
      skip in ScalaJSKeys.packageJSDependencies := false
    )
  ).dependsOn(core)

  lazy val googleAPI = Project(id = "googleAPI",
    base = file("googleAPI"),
    settings = defaults ++ List(
      name := "googleAPI",
      libraryDependencies ++= Seq(
        "com.google.gdata" % "core" % "1.47.1",
        "com.google.api-client" % "google-api-client" % "1.19.1"
      )
    )
  )

  lazy val examples = Project(id = "examples",
    base = file("examples"),
    settings = defaults ++ List(
      publishArtifact := false
    )
  ).aggregate(simpleForm, simpleFormPlay)

  lazy val simpleForm = Project(id = "simpleForm",
    base = file("examples/simpleForm"),
    settings = defaults ++ scalaJSSettings ++ List(
      name := "first.form",
      libraryDependencies ++= Seq(
        "org.scala-lang.modules.scalajs" %%% "scalajs-jquery" % "0.6",
        "joda-time" % "joda-time" % "2.0",
        "com.github.nscala-time" %% "nscala-time" % "1.6.0"
      ),
      skip in ScalaJSKeys.packageJSDependencies := false,
      publishArtifact := false
    )
  ).dependsOn(core, googleAPI)

  lazy val simpleFormPlay = Project(id = "simpleFormPlay",
    base = file("examples/simpleFormPlay"),
    settings = defaults ++ List(
      name := "examples.simpleFormPlay",
      libraryDependencies ++= Seq(
        "com.typesafe.play.plugins" %% "play-plugins-mailer" % "2.3.6-SNAPSHOT",
        "org.mongodb" %% "casbah" % "2.7.4",
        "joda-time" % "joda-time" % "2.0",
        "com.github.nscala-time" %% "nscala-time" % "1.6.0"
      ),
      publishArtifact := false
    )
  ).dependsOn(simpleForm).enablePlugins(play.PlayScala)

}
