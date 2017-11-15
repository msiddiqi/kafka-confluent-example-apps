import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.mehrofiq.SensorHeartbeat
import org.mehrofiq.kafka.ConfluentProperties

/**
  * Created by muhammadsiddiqi on 11/10/17.
  */
object MainApp extends App {

  println ("sdf")

  val sensors =
    Array[SensorInfo](
      SensorInfo("Sensor1", "Sensor1-heartbeat"),
      SensorInfo("Sensor2", "Sensor2-heartbeat"),
      SensorInfo("Sensor3", "Sensor3-heartbeat"),
      SensorInfo("Sensor4", "Sensor4-heartbeat")
    )

  //val sensorIds

  val properties = ConfluentProperties.get()
  val kafkaProducer = new KafkaProducer[String, SensorHeartbeat](properties)

  val messages: Seq[(String, String, SensorHeartbeat)] =
    (0 to 3).flatMap(item => {

      val sensorItem = sensors(item)
      val sensorId : CharSequence = sensorItem.sensorId
      val sensorTopic = sensorItem.topic

      (0 to 5).map(messageItem =>{

        val heartbeatBuilder = SensorHeartbeat.newBuilder()
        heartbeatBuilder.setSensorId(sensorId)

        val key = s"$sensorTopic-$messageItem"
        val message = heartbeatBuilder.build()

        (sensorTopic, key, message)
      })
    })

  messages.foreach({ case (messageTopic: String, messageKey: String, message: SensorHeartbeat) => {
      val producerRecord =
        new ProducerRecord[String, SensorHeartbeat](messageTopic, messageKey, message)

      kafkaProducer.send(producerRecord)
    }})

  kafkaProducer.flush()

  println("tst")
}

case class SensorInfo(sensorId: String, topic: String)

// Muhammads-MacBook-Pro:kafka muhammadsiddiqi$ java -jar avro-tools-1.8.2.jar compile schema ./src/avro_schemas/Heartbeat.avsc ./src/main/java/
