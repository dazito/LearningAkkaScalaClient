name := """akkademy-db-client"""

version := "1.0"

scalaVersion := "2.11.7"

// Change this to another test framework if you prefer
libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "2.2.4" % "test",
    "com.dazito.scala.akkademy-db" %% "akkademy-db" % "0.0.1-SNAPSHOT",
    "com.typesafe.akka" %% "akka-actor" % "2.3.6",
    "com.typesafe.akka" %% "akka-cluster" % "2.3.6",
    "com.typesafe.akka" %% "akka-contrib" % "2.3.6"
)

// Uncomment to use Akka
//libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.3.11"

