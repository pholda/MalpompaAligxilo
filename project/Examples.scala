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
  ).aggregate(/*simpleForm, simpleFormPlay, */i18nForm, i18nFormPlay, exampleDsl, exampleDslPlay)


  lazy val i18nForm = Project(id = "exampleI18nForm",
    base = file("examples/i18nForm"),
    settings = defaults ++ List(
      name := "examples.i18n-form",
      libraryDependencies ++= Seq(
        "joda-time" % "joda-time" % "2.0",
        "be.doeraene" %%% "scalajs-jquery" % "0.8.0",
        "tv.cntt" %% "scaposer" % "1.5",
        "biz.enef" %%% "scalajs-angulate" % scalajsAngulateVersion
      ),
      skip in packageJSDependencies := false,
      publishArtifact := false
    )
  ).dependsOn(core.js, core.jvm, googleAPI, templates).enablePlugins(ScalaJSPlugin)


  lazy val i18nFormPlay = Project(id = "exampleI18nFormPlay",
    base = file("examples/i18nFormPlay"),
    settings = defaults ++ List(
      name := "examples.i18nFormPlay",
      libraryDependencies ++= Seq(
        "com.typesafe.play" %% "play-mailer" % "2.4.0",
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
  ).dependsOn(i18nForm, core.jvm, templates).enablePlugins(play.PlayScala)


  lazy val exampleDsl = project.in(file("examples/dsl")).settings(defaults:_*).settings(
    publishArtifact := false,
    libraryDependencies ++= Seq(
      "com.lihaoyi" %% "upickle" % "0.2.8",
      "com.greencatsoft" %%% "scalajs-angular" % "0.6",
      "be.doeraene" %%% "scalajs-jquery" % "0.8.0"
    )
  ).dependsOn(coreJS, coreJVM, dslJS, dslJVM).enablePlugins(ScalaJSPlugin)

  lazy val exampleDslPlay = project.in(file("examples/dslPlay")).settings(defaults:_*).settings(
    publishArtifact := false,
    scalajsOutputDir := (classDirectory in Compile).value / "public" / "javascripts",
    /**
     * copying scalajs output to public/javascript. It should be done better
     */
    compile in Compile <<= (compile in Compile) dependsOn (fastOptJS in (exampleDsl, Compile)),
    dist <<= dist dependsOn (fullOptJS in (exampleDsl, Compile)),
    stage <<= stage dependsOn (fullOptJS in (exampleDsl, Compile))

  ).settings(Seq(packageScalaJSLauncher, fastOptJS, fullOptJS) map { packageJSKey =>
      crossTarget in (exampleDsl, Compile, packageJSKey) := scalajsOutputDir.value
  }).dependsOn(core.jvm, templates, dsl.jvm, exampleDsl).enablePlugins(play.PlayScala)
}