scalaVersion := "2.10.5"
crossScalaVersions := Seq("2.10.5", "2.11.7")

libraryDependencies ++= Seq(
  "com.twitter" %% "finagle-core" % "6.28.0",
  "org.scalacheck" %% "scalacheck" % "1.12.4" % "test",
  "org.scalatest" %% "scalatest" % "2.2.5" % "test"
)
