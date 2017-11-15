name := "kafka"

version := "1.0"

scalaVersion := "2.12.4"

libraryDependencies += "org.apache.avro" % "avro" % "1.8.2"
libraryDependencies += "org.apache.kafka" % "kafka-clients" % "0.11.0.0"
libraryDependencies += "io.confluent" % "kafka-avro-serializer" % "1.0" //exclude ("log4j", "log4j") exclude ("org.slf4j", "slf4j-logj12")
//libraryDependencies += "org.apache.kafka" % "connect-api" % "0.11.0.0"


resolvers ++= Seq(
  "confluent" at "http://packages.confluent.io/maven/",
  Resolver.sonatypeRepo("public"),
  "Sonatype OSS Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots"
)

