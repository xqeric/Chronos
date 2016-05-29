import AssemblyKeys._  // put this at the top of the file

assemblySettings

name := "chronoslearn"
version := "1.0"
scalaVersion := "2.10.6"
jarName := "chronoslearn.jar"

//provided
//libraryDependencies ++= Seq(
libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "1.6.0"  % "provided",
  "com.databricks" % "spark-csv_2.10" % "1.4.0",
  "org.apache.spark" %% "spark-sql" % "1.6.0"  % "provided",
  "org.apache.spark" %% "spark-streaming" % "1.6.0"  % "provided" ,
  "org.apache.spark" %% "spark-streaming-kafka" % "1.6.0" ,
  "org.apache.commons" % "commons-pool2" % "2.3",
  "io.jvm.uuid" %% "scala-uuid" % "0.2.1",
  "com.datastax.spark" %% "spark-cassandra-connector" % "1.5.0"
).map(
  _.excludeAll(ExclusionRule(organization = "org.mortbay.jetty"))
)


mergeStrategy in assembly := {
  case "META-INF/io.netty.versions.properties"         => MergeStrategy.first
  case "org/apache/spark/unused/UnusedStubClass.class" => MergeStrategy.first
  case "META-INF/maven/com.google.guava/guava/pom.properties"  => MergeStrategy.first
  case "META-INF/maven/com.google.guava/guava/pom.xml" => MergeStrategy.first
  case "META-INF/maven/org.apache.avro/avro-ipc/pom.properties" => MergeStrategy.last
  case "META-INF/maven/org.slf4j/slf4j-api/pom.properties" => MergeStrategy.first
  case "META-INF/maven/org.slf4j/slf4j-api/pom.xml" => MergeStrategy.first

  case x =>
    val oldStrategy = (mergeStrategy in assembly).value
    oldStrategy(x)
}