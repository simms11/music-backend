name := """music-backend"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.3"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test
libraryDependencies += "org.mongodb.scala" %% "mongo-scala-driver" % "4.1.0"
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.7.4"
libraryDependencies += "org.scalaj" %% "scalaj-http" % "2.4.2"

// only for Play 2.7.x (Scala 2.12)
libraryDependencies ++= Seq(
  "org.reactivemongo" %% "play2-reactivemongo" % "0.20.11-play27"
)

// only for Play 2.6.x (Scala 2.12)
libraryDependencies ++= Seq(
  "org.reactivemongo" %% "play2-reactivemongo" % "0.20.11-play26"
)

// only for Play 2.5.x (Scala 2.11)
libraryDependencies ++= Seq(
  "org.reactivemongo" %% "play2-reactivemongo" % "0.20.11-play25"
)


// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.example.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.example.binders._"
