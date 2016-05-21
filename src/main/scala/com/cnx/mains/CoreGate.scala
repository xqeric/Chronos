package com.cnx.mains

import com.cnx.SmartAirline
import com.cnx.kafka.{DataReceivers, TopicsManager}



object CoreGate {
  
  def main(args: Array[String]){

  val sal = new SmartAirline()
    sal.DoAirlineJob()
     
  }
  
}