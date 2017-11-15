package org.mehrofiq.kafka

import java.util.Properties

import io.confluent.kafka.serializers.KafkaAvroSerializer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer

object ConfluentProperties {

  def get() : Properties = {

    val properties = new Properties()

    properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
    properties.put(ProducerConfig.CLIENT_ID_CONFIG, "Sensor-x1-Producer")
    properties.put("schema.registry.url", "http://localhost:8081")

    properties.put(ProducerConfig.ACKS_CONFIG, "all")
    properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer])
    properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, classOf[KafkaAvroSerializer])

    properties
  }
}
