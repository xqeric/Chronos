package com.cnx.kafka

import org.apache.kafka.clients.producer.{ProducerConfig, KafkaProducer, ProducerRecord}
import org.apache.spark.streaming._
import org.apache.spark.streaming.kafka._
import org.apache.spark.{SparkConf, SparkContext}
import java.util.HashMap
import java.util.Properties

/**
 * this function class will check if the specific topic is exist or not..
 * and create topic in kakfka server 
 */
class TopicsManager(sc: SparkContext) {
  
  /**
   * check the topic exist in the current kafka
   */
  def isTopicExist(topic:String){
    
    
  }
  
  
  def sendMsg(topic:String){
    
    val config = new Properties
    config.put("metadata.broker.list", "172.17.42.1:9092")
    val producerApps = new KafkaProducerApp("172.17.42.1:9092", config, defaultTopic = Option("tests"))
   
    for(i<- 0 to 1000000000)
    producerApps.send((i+"_testwork").getBytes, topic)
  }
  
  /**
   * create kakfa topic
   */
  def createTopic(topic:String): Int = {
    
    // Zookeeper connection properties
    val props = new HashMap[String, Object]()
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.17.42.1:2181")
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
      "org.apache.kafka.common.serialization.StringSerializer")
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
      "org.apache.kafka.common.serialization.StringSerializer")

    val producer = new KafkaProducer[String, String](props)
    val messagesPerSec = 100
    // Send some messages
    
    val message = new ProducerRecord[String, String](topic, null, "Good started")
    producer.send(message)
        
    return 0
  }
  
}