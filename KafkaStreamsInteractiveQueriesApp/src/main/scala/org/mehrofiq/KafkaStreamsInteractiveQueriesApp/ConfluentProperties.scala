package org.mehrofiq.kafkaMergerApp

import java.util.Properties

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.streams.StreamsConfig

trait ConfluentProperties {
  def get() : Properties
}

object ConfluentProperties{

  def get() : Properties = {

    val properties = new Properties()

    properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
    properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "Sensor-x1-Producer_1")
    properties.put("schema.registry.url", "http://localhost:8081")
    properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")

    val rpcEndPoint = "localhost:4040"
    properties.put(StreamsConfig.APPLICATION_SERVER_CONFIG, rpcEndPoint)

    properties
  }
}
