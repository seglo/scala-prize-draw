lazy val `scala-prize-draw` = (project in file(".")).
  settings(
    name := "scala-prize-draw",
    version := "1.0",
    scalaVersion := "2.11.4",
    libraryDependencies ++= Seq(
      "net.databinder.dispatch" %% "dispatch-lift-json" % "0.11.3",
      "com.typesafe" % "config" % "1.3.0",
      "org.specs2" %% "specs2-core" % "3.6" % "test"
    ),
    resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases",
    scalacOptions in Test ++= Seq("-Yrangepos")
)
