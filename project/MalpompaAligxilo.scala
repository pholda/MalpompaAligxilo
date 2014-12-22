import sbt._
import Keys._
import scalajs.sbtplugin.ScalaJSPlugin._

object MalpompaAligxilo extends Build {
  lazy val root = Project(id = "MalpompaAligxilo",
    base = file("."),
    settings = Defaults.defaultSettings ++ List(
      scalaVersion := "2.11.1"
    )
  ).aggregate(frontGenerator, core, simpleBack)

  lazy val frontGenerator = Project(id = "FrontGenerator",
    base = file("frontGenerator"),
    settings = Defaults.defaultSettings ++ scalaJSSettings ++ List(
      libraryDependencies += "org.scala-lang.modules.scalajs" %%% "scalajs-jquery" % "0.6",
      skip in ScalaJSKeys.packageJSDependencies := false,
      scalaVersion := "2.11.1"
    )
  ).dependsOn(core)

  lazy val core = Project(id = "Core",
    base = file("core"),
    settings = Defaults.defaultSettings ++ scalaJSSettings ++ List(
      libraryDependencies += "org.scala-lang.modules.scalajs" %%% "scalajs-jquery" % "0.6",
      skip in ScalaJSKeys.packageJSDependencies := false,
      scalaVersion := "2.11.1"
    )
  )

  lazy val simpleBack = Project(id = "SimpleBack",
    base = file("simpleBack"),
    settings = Defaults.defaultSettings
  )
}
