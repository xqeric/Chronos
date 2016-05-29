package com.cnx

import org.apache.spark.{SparkConf, SparkContext}
import com.cnx.datagen._
import com.cnx.kafka.{DataReceivers, TopicsManager}
import com.cnx.bitcassandra._

import org.apache.spark.streaming.kafka._
import org.apache.spark.streaming._


/**
  * Created by qiang on 16-5-21.
  */
class SmartAirline {

  /**
    * this this the entry of process aireline data
    *
    */
  def DoAirlineJob() = {

    val conf = new SparkConf()
      .setAppName("SparkJoins")
      //.setMaster("local[20]")
        .setMaster("spark://192.168.1.113:7077")
      //.set("spark.streaming.clock", "org.apache.spark.streaming.util.ManualClock")
      //.set("spark.executor.memory", "1g")
      //.set("spark.cores.max", "1")
      .set("spark.cassandra.connection.host", "192.168.1.112")
      .set("spark.cassandra.connection.port","9042")

    //val dh = new DataHelper(conf)
    //dh.createAirelineSpace();
    //dh.saveMsg()

    val sc = new SparkContext(conf)
    val dr = new DataReceivers(sc)
    dr.createAirelineSpace()
    dr.directlyDataFromKafka("topic2")


  }

}
