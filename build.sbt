import AssemblyKeys._  // put this at the top of the file

assemblySettings

name := "chronoslearn"
version := "1.0"
scalaVersion := "2.10.6"


//libraryDependencies ++= Seq(
libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "1.6.0" % "provided",
  "com.databricks" % "spark-csv_2.10" % "1.4.0",
  "org.apache.spark" %% "spark-sql" % "1.6.0" % "provided",
  "org.apache.spark" %% "spark-streaming" % "1.6.0" % "provided",
  "org.apache.spark" %% "spark-streaming-kafka" % "1.6.0",
  "org.apache.commons" % "commons-pool2" % "2.3",
  "com.datastax.spark" %% "spark-cassandra-connector" % "1.5.0"
).map(
  _.excludeAll(ExclusionRule(organization = "org.mortbay.jetty"))
)


mergeStrategy in assembly := {
  case "META-INF/io.netty.versions.properties"         => MergeStrategy.first
  case "org/apache/spark/unused/UnusedStubClass.class" => MergeStrategy.first
  case "unwanted.txt"                                => MergeStrategy.discard
  case x =>
    val oldStrategy = (mergeStrategy in assembly).value
    oldStrategy(x)
}