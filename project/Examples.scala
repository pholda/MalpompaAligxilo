import play.twirl.sbt.SbtTwirl
import sbt.Keys._
import sbt._
import org.scalajs.sbtplugin.ScalaJSPlugin
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import com.typesafe.sbt.packager.universal.UniversalKeys

object Examples extends Build with UniversalKeys
{
  import MalpompaAligxilo._

  lazy val examples = Project(id = "examples",
    base = file("examples"),
    settings = defaults ++ List(
      publishArtifact := false
    )
  ).aggregate(simpleForm, simpleFormPlay, i18nForm, i18nFormPlay)

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
  ).dependsOn(simpleForm, core, twirlTemplates).enablePlugins(play.PlayScala)


  lazy val i18nForm = Project(id = "i18nForm",
    base = file("examples/i18nForm"),
    settings = defaults ++ List(
      name := "examples.i18n-form",
      libraryDependencies ++= Seq(
        "joda-time" % "joda-time" % "2.0",
        "be.doeraene" %%% "scalajs-jquery" % "0.8.0",
        "tv.cntt" %% "scaposer" % "1.5"
      ),
      skip in packageJSDependencies := false,
      publishArtifact := false
    )
  ).dependsOn(core, googleAPI).enablePlugins(ScalaJSPlugin)


  lazy val i18nFormPlay = Project(id = "i18nFormPlay",
    base = file("examples/i18nFormPlay"),
    settings = defaults ++ List(
      name := "examples.i18nFormPlay",
      libraryDependencies ++= Seq(
        "com.typesafe.play.plugins" %% "play-plugins-mailer" % "2.3.6-SNAPSHOT",
        "org.mongodb" %% "casbah" % "2.7.4"
      ),
      publishArtifact := false,
      scalajsOutputDir := (classDirectory in Compile).value / "public" / "javascripts",
      /**
       * copying scalajs output to public/javascript. It should be done better
       */
      compile in Compile <<= (compile in Compile) dependsOn (fastOptJS in (i18nForm, Compile)),
      dist <<= dist dependsOn (fullOptJS in (i18nForm, Compile)),
      stage <<= stage dependsOn (fullOptJS in (i18nForm, Compile))

    ) ++ (
      Seq(packageScalaJSLauncher, fastOptJS, fullOptJS) map { packageJSKey =>
        crossTarget in (i18nForm, Compile, packageJSKey) := scalajsOutputDir.value
      })
  ).dependsOn(i18nForm, core, twirlTemplates).enablePlugins(play.PlayScala)
}