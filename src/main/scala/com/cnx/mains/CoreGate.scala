package com.cnx.mains

import org.apache.spark._
import com.cnx.datagen._
import com.cnx.kafka.{DataReceivers, TopicsManager}
import com.cnx.bitcassandra._
import com.fasterxml.jackson.annotation.ObjectIdGenerators.None
import org.apache.spark.streaming.kafka._
import org.apache.spark.streaming._


object CoreGate {
  
  def main(args: Array[String]){
   
    val conf = new SparkConf()
      .setAppName("SparkJoins")
      .setMaster("local[2]")
      .set("spark.streaming.clock", "org.apache.spark.streaming.util.ManualClock")
      .set("spark.executor.memory", "3g")
      .set("spark.cores.max", "1")
      .set("spark.cassandra.connection.host", "127.0.0.1")
      .set("spark.cassandra.connection.port","32771")
    
    
      
    //val dh = new DataHelper(conf)
    //dh.createKeySpace()
    //dh.saveMsg(null)
    
    
    //sc.parallelize(seq, numSlices).s
    
    //val tm = new TopicsManager(sc)
    //tm.sendMsg("tests")
    val sc = new SparkContext(conf)
    val dr = new DataReceivers(sc)
    dr.directlyDataFromKafka("topic2");
    //dr.directlyDataFromKafka("tests")
    
    //val airline = new AirlineDataJob(sc)
    //airline.runAirlineDataJob("/usr/sparkwork/chronos/data/1987.csv")
     
  }
  
}