/*
package org.mehrofiq.kafkaMergerApp

import java.util
import java.util.HashMap

import io.confluent.kafka.streams.serdes.avro.{GenericAvroSerde, SpecificAvroSerde}
import org.apache.avro.specific.SpecificRecord

class SerdeBuilderImpl extends SerdeBuilder {

  private val properties : HashMap[String, String] = new util.HashMap[String, String]()
  properties.put("schema.registry.url", "http://localhost:8081")

  override def specificAvroSerde[T <: SpecificRecord](isKey: Boolean) = {
    val retSerde = new SpecificAvroSerde[T]()
    retSerde.configure(properties, isKey)

    retSerde
  }

  override def genericAvroSerde(isKey: Boolean) = {
    val retSerde = new GenericAvroSerde()
    retSerde.configure(properties, isKey)

    retSerde
  }
}
*/
