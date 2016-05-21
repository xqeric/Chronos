package com.cnx.kafka
import kafka.serializer.StringDecoder
import org.apache.spark._
import org.apache.spark.sql.DataFrame
import org.apache.spark.streaming.{StreamingContext, _}
import org.apache.spark.streaming.kafka.{KafkaUtils, _}
import org.apache.spark.streaming.scheduler.StreamingListener

//import  org.apache.kafka.common.message. //kafka.serializer.StringDecoder

/**
 * fetch data from Apache kafka service
 * 
 */
class DataReceivers(sc: SparkContext) {
  
  /**
   * access the kakfa data service
   */
  def receiveDataFromKafka(topic:String):DataFrame = {
    //create spark streaming..
    val ssc: StreamingContext = new StreamingContext(sc, Seconds(2))
    val mytopic = topic.map { x => x }
    //val kafkaParams = Map("metadata.broker.list" -> "172.17.42.1:9092", "metadata.broker.list" -> "172.17.42.1:9092")
    //val kafkaStream = KafkaUtils.createStream(ssc, kafkaParams, topic, StorageLevels.MEMORY_AND_DISK)

    
    return null;
  }
  
  def directlyDataFromKafka(topic:String): DataFrame={
    
    //create spark streaming..
    val ssc = new StreamingContext(sc, Seconds(2))
    // Define the Kafka parameters, broker list must be specified
    //val kafkaParams = Map[String, String]("metadata.broker.list" -> "192.168.1.111:9092",
    //  "group.id" -> "test-consumer-group",
     // "zookeeper.connect" -> "192.168.1.111:2181")
    val kafkaParams = Map[String, String]("metadata.broker.list" -> "192.168.1.113:9092")
        //"group.id" -> "0"
 
    // Define which topics to read from
    val topics = Set(topic)
    //ssc.checkpoint("/tmp")
    //ssc.addStreamingListener(new RuleFileListenerB())
    // Create the direct stream with the Kafka parameters and topics
    val kafkaStream = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](ssc, kafkaParams, topics)

    System.out.println(kafkaStream.toString())
    //val kafkaStream = KafkaUtils.createDirectStream[Array[Byte], Array[Byte], DefaultDecoder, DefaultDecoder](ssc, kafkaParams, topics)
  
    // Define the offset ranges to read in the batch job
    val offsetRanges = Array(
      OffsetRange(topic, 0, 10, 2000000))
      // Create the RDD based on the offset ranges
     val rdd = KafkaUtils.createRDD[String, String, StringDecoder, StringDecoder](sc, kafkaParams, offsetRanges)
 

     rdd.foreachPartition ( partitionOfRecords => partitionOfRecords.foreach(System.out.println))
    
    //ssc.start()
    //ssc.awaitTermination()
    
     return null
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