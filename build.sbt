name := "karusel"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "org.scalaz" %% "scalaz-core" % "7.1.4",
  "org.scalaz" %% "scalaz-effect" % "7.1.4",
  "org.scalaz" %% "scalaz-typelevel" % "7.1.4",
  "org.scalaz" %% "scalaz-scalacheck-binding" % "7.1.4" % "test",
  "com.typesafe.akka" %% "akka-actor" % "2.4.0",
  "com.rabbitmq" % "amqp-client" % "3.5.5"
)




    