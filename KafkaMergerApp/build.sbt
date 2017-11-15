name := "KafkaMergerApp"

version := "0.1"

scalaVersion := "2.12.4"

resolvers ++= Seq(
  "confluent" at "http://packages.confluent.io/maven/",
  Resolver.sonatypeRepo("public"),
  "Sonatype OSS Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
  "scalaz-bintray" at "https://d1.bintray.com/scalaz/releases",
  "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/",
  "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
  Resolver.bintrayRepo("cakesolutions", "maven")
)

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3"
libraryDependencies += "ch.qos.logback" % "logback-core" % "1.2.3"
libraryDependencies += "org.apache.avro" % "avro" % "1.8.2"
libraryDependencies += "org.apache.kafka" % "kafka-clients" % "0.11.0.1"
libraryDependencies += "org.apache.kafka" % "kafka-streams" % "0.11.0.1"
libraryDependencies += "io.confluent" % "kafka-streams-avro-serde" % "3.3.0" exclude("log4j", "log4j") exclude("org.sl4j", "sl4j-log4j12")
libraryDependencies += "io.confluent" % "kafka-avro-serializer" % "3.3.0" exclude("log4j", "log4j") exclude("org.sl4j", "sl4j-log4j12")

        