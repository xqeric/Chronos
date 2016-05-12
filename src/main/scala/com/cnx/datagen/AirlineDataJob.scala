package com.cnx.datagen

import org.apache.spark._
import System.out
import org.apache.spark.rdd.RDD
import scala.xml.dtd.DFAContentModel
import com.sun.corba.se.impl.activation.ListORBs


class AirlineDataJob(sc: SparkContext) {

  def runAirlineDataJob(t:String) {
    val readCsv = new ReadCsv(sc);
    //read all item from csv file
    val dfAirLine = readCsv.DoReadCsv(t)
    out.println("The item count of before sampled is : "+dfAirLine.count())
    //sampled the data as per the 1 percent
    val dfSample = dfAirLine.sample(false, 0.001)
    out.println("The item count of after sampled is : "+dfSample.count())
    
    dfSample.show();
    val dest = dfSample.select("Dest", "Year","Month","DayofMonth").takeAsList(1).get(0).getString(0)
    out.println(dest)
    
    
  }
  
}