package com.cnx.bitcassandra
import org.apache.spark.streaming._
import org.apache.spark.streaming.kafka._
import org.apache.spark.{SparkConf, SparkContext}
import java.util.HashMap
import java.util.Properties
import com.datastax.spark.connector._
import com.datastax.spark.connector.cql.CassandraConnector

import com.datastax.spark.connector.streaming._
import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.DataFrame
import io.jvm.uuid.UUID





/**
 *  add this comments for real.
 */
class DataHelper(conf:SparkConf) {
  
  def iniDataTable(){
    
  }
  
  def createKeySpace()={
    /** Creates the keyspace and table in Cassandra. */
  CassandraConnector(conf).withSessionDo { session =>
    session.execute(s"DROP KEYSPACE IF EXISTS kafka_streaming")
    session.execute(s"CREATE KEYSPACE IF NOT EXISTS kafka_streaming WITH REPLICATION = {'class': 'SimpleStrategy', 'replication_factor': 1 }")
    session.execute(s"CREATE TABLE IF NOT EXISTS kafka_streaming.wordcount (word TEXT PRIMARY KEY, count int)")
    session.execute(s"TRUNCATE kafka_streaming.wordcount")
  }


  }




  def createAirelineSpace()={
    /** Creates the keyspace and table in Cassandra. */
    CassandraConnector(conf).withSessionDo { session =>
      session.execute(s"DROP KEYSPACE IF EXISTS kafka_streaming")
      session.execute(s"CREATE KEYSPACE IF NOT EXISTS kafka_streaming WITH REPLICATION = {'class': 'SimpleStrategy', 'replication_factor': 1 }")
      session.execute(s"CREATE TABLE IF NOT EXISTS kafka_streaming.airline (id TEXT PRIMARY KEY)")
      session.execute(s"TRUNCATE kafka_streaming.airline")
    }
  }
  
  def saveMsg()={
    val sc = new SparkContext(conf)




    //val collection = sc.parallelize(Seq(UUID.random.toString(), i.toString), ("cat"+i.toString(), i), ("caw"+i.toString(), i), ("mouse"+i.toString(), i)))

    //val i=2
    //val collection
    var i:Int = 0

    for(i<-0 to 10000){

      //val collection = sc.parallelize(Seq("abc"+i.toString, i.toString), ("abc"+i.toString, i))

      //collection.saveToCassandra("kafka_streaming", "airline")//,SomeColumns("id","city"))
    }

    
  }
}