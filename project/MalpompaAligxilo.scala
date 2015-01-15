import sbt._
import Keys._
import scalajs.sbtplugin.ScalaJSPlugin._

object MalpompaAligxilo extends Build {
  val defaults = Defaults.coreDefaultSettings ++ List(
    organization := "pl.pej",
    version := "0.1-SNAPSHOT",
    scalaVersion := "2.11.1"
  )

  lazy val root = Project(id = "MalpompaAligxilo",
    base = file("."),
    settings = Defaults.defaultSettings ++ List(
      name := "malpompaAligxilo",
      scalaVersion := "2.11.1"
    )
  ).aggregate(jes2015, core, playBackend, semajnfino)

  lazy val jes2015 = Project(id = "jes2015",
    base = file("jes2015"),
    settings = defaults ++ scalaJSSettings ++ List(
      name := "jes2015",
      libraryDependencies ++= Seq(
        "org.scala-lang.modules.scalajs" %%% "scalajs-jquery" % "0.6",
        "joda-time" % "joda-time" % "2.0",
        "com.github.nscala-time" %% "nscala-time" % "1.6.0"
      ),
      skip in ScalaJSKeys.packageJSDependencies := false
    )
  ).dependsOn(core)

  lazy val semajnfino = Project(id = "semajnfino",
    base = file("semajnfino"),
    settings = defaults ++ scalaJSSettings ++ List(
      name := "semajnfino",
      libraryDependencies ++= Seq(
        "org.scala-lang.modules.scalajs" %%% "scalajs-jquery" % "0.6",
        "joda-time" % "joda-time" % "2.0",
        "com.github.nscala-time" %% "nscala-time" % "1.6.0"
      ),
      skip in ScalaJSKeys.packageJSDependencies := false
    )
  ).dependsOn(core)

  lazy val core = Project(id = "Core",
    base = file("core"),
    settings = defaults ++ scalaJSSettings ++ List(
      name := "core",
      libraryDependencies ++= Seq(
        "org.scala-lang.modules.scalajs" %%% "scalajs-jquery" % "0.6",
        "joda-time" % "joda-time" % "2.0",
        "com.github.nscala-time" %% "nscala-time" % "1.6.0"
      ),
      skip in ScalaJSKeys.packageJSDependencies := false
    )
  )

  lazy val playBackend = Project(
    id = "PlayBackend",
    base = file("playBackend"),
    settings = defaults ++ List(
      name := "playBackend",
      libraryDependencies ++= Seq(
        "com.typesafe.play.plugins" %% "play-plugins-mailer" % "2.3.6-SNAPSHOT",
        "org.mongodb" %% "casbah" % "2.7.4",
        "joda-time" % "joda-time" % "2.0",
        "com.github.nscala-time" %% "nscala-time" % "1.6.0"
      )
    )
  ).dependsOn(core, jes2015, semajnfino).enablePlugins(play.PlayScala)
}
