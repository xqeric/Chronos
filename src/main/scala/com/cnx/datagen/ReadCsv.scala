package com.cnx.datagen
import com.databricks.spark.csv._
import com.databricks.spark.csv.util._
import org.apache.spark._
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{SQLContext, DataFrame}
import org.apache.spark.sql.DataFrame


/**
 * load csv file from specific path
 * Author: Qiang
 * version : 2016 Apr 1st
 */
class ReadCsv(sc: SparkContext) {
  
  /**
   * read csv file and retrurn dataframe object..
   * 
   */
  def DoReadCsv(p:String): DataFrame ={ 
    val sqlContext = new SQLContext(sc)
    
    val df = sqlContext.read
      .format("com.databricks.spark.csv")
      .option("header", "true") 
      .option("inferSchema", "true")
      .load(p)
    
      return df
  }
  
  /**
   * read csv file as rdd object
   */
  def DoReadCsvAsRDD(p:String) : RDD[String] = {
    return sc.textFile(p,2)
  }
 
  
}