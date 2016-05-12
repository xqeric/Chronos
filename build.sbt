name := "chronoslearn"

version := "0.1"

scalaVersion := "2.10.6"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "1.6.0" % "provided",
  "org.scalatest" %% "scalatest" % "1.9.1" % "test",
  "org.slf4j" % "slf4j-log4j12" % "1.6.1",
  "com.databricks" % "spark-csv_2.10" % "1.4.0",
  "org.apache.spark" %% "spark-sql" % "1.6.0",
  "org.apache.spark" %% "spark-streaming" % "1.6.0",
  "org.apache.spark" %% "spark-streaming-kafka" % "1.6.0",
  "org.apache.spark" %% "spark-streaming-flume" % "1.6.0",
  "org.apache.spark" %% "spark-mllib" % "1.6.0",
  "org.apache.commons" % "commons-pool2" % "2.3",
  "com.datastax.spark" %% "spark-cassandra-connector" % "1.6.0-M2"
)

resolvers ++= Seq(
   "Spray Repository" at "http://repo.spray.cc/",
   "Akka Repository" at "http://repo.akka.io/releases/"
)
