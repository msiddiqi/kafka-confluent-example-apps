
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.{KafkaStreams, StreamsConfig}
import org.apache.kafka.streams.kstream.{KStream, KStreamBuilder}
import org.apache.kafka.streams.processor.TopologyBuilder.AutoOffsetReset
import org.mehrofiq.SensorHeartbeat
import org.mehrofiq.kafkaMergerApp.{ConfluentProperties, SerdeBuilder, SerdeBuilderImpl}

object MainApp extends App {

  val confluentProperties = ConfluentProperties
  val properties = confluentProperties.get()

  val streamConfig : StreamsConfig = new StreamsConfig(properties)
  val streamBuilder : KStreamBuilder = new KStreamBuilder()

  val serdeBuilder : SerdeBuilder = new SerdeBuilderImpl()
  val heartbeatSerde = serdeBuilder.specificAvroSerde[SensorHeartbeat](false);

  val sensor1Stream =  getSensorStream("Sensor1-heartbeat")
  val sensor2Stream =  getSensorStream("Sensor2-heartbeat")
  val sensor3Stream =  getSensorStream("Sensor3-heartbeat")
  val sensor4Stream =  getSensorStream("Sensor4-heartbeat")

  val mergedStream = streamBuilder.merge(sensor1Stream, sensor2Stream, sensor3Stream, sensor4Stream)

  mergedStream.to(Serdes.String(), heartbeatSerde, "Merged-SensorsHeartbeat")

  val kafkaStreams = new KafkaStreams(streamBuilder, streamConfig)
  kafkaStreams.start()

  Runtime.getRuntime.addShutdownHook(new Thread(() => {
    kafkaStreams.close()
  }))

  private def getSensorStream(topic: String) : KStream[String, SensorHeartbeat] = {

    val sensorStream =
      streamBuilder.stream[String, SensorHeartbeat](
        AutoOffsetReset.EARLIEST,
        Serdes.String(),
        heartbeatSerde,
        topic
      )

    sensorStream
  }
}



