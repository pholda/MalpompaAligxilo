import sbt.Keys._
import sbt._
import org.scalajs.sbtplugin.ScalaJSPlugin
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import com.typesafe.sbt.packager.universal.UniversalKeys

object MalpompaAligxilo extends Build with UniversalKeys {
  val defaults = Defaults.coreDefaultSettings ++ List(
    organization := "pl.pej.malpompaaligxilo",
    version := "0.1.1-SNAPSHOT",
    scalaVersion := "2.11.5",
    libraryDependencies ++= List(
      "org.scalatest" % "scalatest_2.11" % "2.2.3" % "test"
    )
  )

  lazy val root = Project(id = "malpompaAligxilo",
    base = file("."),
    settings = defaults ++ List(
      publishArtifact := false
    )
  ).aggregate(core, googleAPI, examples)

  lazy val core = Project(id = "core",
    base = file("core"),
    settings = defaults ++ List(
      name := "core",
      libraryDependencies ++= Seq(
        "org.scala-lang.modules" % "scala-parser-combinators_2.11" % "1.0.3",
        "joda-time" % "joda-time" % "2.0"
      )
    )
  ).enablePlugins(ScalaJSPlugin)

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

  val scalajsOutputDir = Def.settingKey[File]("directory for javascript files output by scalajs")

  lazy val simpleForm = Project(id = "simpleForm",
    base = file("examples/simpleForm"),
    settings = defaults ++ List(
      name := "examples.simple-form",
      libraryDependencies ++= Seq(
        "joda-time" % "joda-time" % "2.0",
        "be.doeraene" %%% "scalajs-jquery" % "0.8.0"
      ),
      skip in packageJSDependencies := false,
      publishArtifact := false
    )
  ).dependsOn(core, googleAPI).enablePlugins(ScalaJSPlugin)

  lazy val simpleFormPlay = Project(id = "simpleFormPlay",
    base = file("examples/simpleFormPlay"),
    settings = defaults ++ List(
      name := "examples.simpleFormPlay",
      libraryDependencies ++= Seq(
        "com.typesafe.play.plugins" %% "play-plugins-mailer" % "2.3.6-SNAPSHOT",
        "org.mongodb" %% "casbah" % "2.7.4"
      ),
      publishArtifact := false,
      scalajsOutputDir := (classDirectory in Compile).value / "public" / "javascripts",
      /**
       * copying scalajs output to public/javascript. It should be done better
       */
      compile in Compile <<= (compile in Compile) dependsOn (fastOptJS in (simpleForm, Compile)),
      dist <<= dist dependsOn (fullOptJS in (simpleForm, Compile)),
      stage <<= stage dependsOn (fullOptJS in (simpleForm, Compile))

    ) ++ (
      Seq(packageScalaJSLauncher, fastOptJS, fullOptJS) map { packageJSKey =>
  crossTarget in (simpleForm, Compile, packageJSKey) := scalajsOutputDir.value
      })
  ).dependsOn(simpleForm, core).enablePlugins(play.PlayScala)

}
