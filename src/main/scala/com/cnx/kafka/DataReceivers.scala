package com.cnx.kafka
import kafka.serializer.StringDecoder
import org.apache.spark._
import org.apache.spark.sql.DataFrame
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.{StreamingContext, _}
import org.apache.spark.streaming.kafka.{KafkaUtils, _}
import org.apache.spark.streaming.scheduler.StreamingListener
import com.datastax.spark.connector._
import com.datastax.spark.connector.cql.CassandraConnector

//import  org.apache.kafka.common.message. //kafka.serializer.StringDecoder

/**
 * fetch data from Apache kafka service
 * 
 */
class DataReceivers(sc: SparkContext) {



  def directlyDataFromKafka(topic: String) = {

    //create spark streaming..
    val ssc = new StreamingContext(sc, Seconds(1))
    ssc.checkpoint("/tmp")

    // Define the Kafka parameters, broker list must be specified
    //val kafkaParams = Map[String, String]("metadata.broker.list" -> "192.168.1.111:9092",
    //  "group.id" -> "test-consumer-group",
    // "zookeeper.connect" -> "192.168.1.111:2181")
    val kafkaParams = Map[String, String]("metadata.broker.list" -> "192.168.1.112:9092")
    //"group.id" -> "0"

    // Define which topics to read from
    val topics = Set(topic)
    //ssc.checkpoint("/tmp")
    //ssc.addStreamingListener(new RuleFileListenerB())
    // Create the direct stream with the Kafka parameters and topics
    val kafkaStream = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](ssc, kafkaParams, topics)

    kafkaStream.foreachRDD(rdd => {

      //rdd.map(a=>a._1).foreach{ data=> System.out.println(data.toString())}
      rdd.map(a=> (a, a)).saveToCassandra("kafka_streaming", "airline");
    }
    )
    /*rdd.foreach{


        data=>  System.out.println("=============="+data.toString()) }

    }
    )


    //val kafkaStream = KafkaUtils.createDirectStream[Array[Byte], Array[Byte], DefaultDecoder, DefaultDecoder](ssc, kafkaParams, topics)

    // Define the offset ranges to read in the batch job
    /val offsetRanges = Array(
      OffsetRange(topic, 0, 100, 200))
      // Create the RDD based on the offset ranges
    val rdd = KafkaUtils.createRDD[String, String, StringDecoder, StringDecoder](sc, kafkaParams, offsetRanges)
 

    rdd.foreachPartition ( partitionOfRecords => partitionOfRecords.foreach(System.out.println))
    // rdd.foreachPartition ( partitionOfRecords => partitionOfRecords.foreach(System.out.println))
    // rdd.foreachPartition ( partitionOfRecords => partitionOfRecords.foreach(System.out.println))

      **/
    ssc.start()
    ssc.awaitTermination()


  }


  def createAirelineSpace() = {
    /** Creates the keyspace and table in Cassandra. */
    CassandraConnector(sc.getConf).withSessionDo { session =>
      session.execute(s"DROP KEYSPACE IF EXISTS kafka_streaming")
      session.execute(s"CREATE KEYSPACE IF NOT EXISTS kafka_streaming WITH REPLICATION = {'class': 'SimpleStrategy', 'replication_factor': 1 }")
      session.execute(s"CREATE TABLE IF NOT EXISTS kafka_streaming.airline (id TEXT PRIMARY KEY, city TEXT)")
      session.execute(s"TRUNCATE kafka_streaming.airline")
    }
  }
}

class RuleFileListenerB extends StreamingListener {

  override def onBatchStarted(batchStarted : org.apache.spark.streaming.scheduler.StreamingListenerBatchStarted) {
    println("---------------------------------------------------------------------------------------------------------------------------------------------")
        println("check whether the file's modified date is change, if change then reload the configuration file")
    //val source = scala.io.Source.fromFile("D:/code/scala/test")
    //val lines = try source.mkString finally source.close()
    //println(lines)
    println("---------------------------------------------------------------------------------------------------------------------------------------------")
  }

}