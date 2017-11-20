import java.lang
import java.util.concurrent.TimeUnit
import java.util.{Timer, TimerTask}

import MainApp.kafkaStreams
import ch.qos.logback.core.util.TimeUtil
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.{KafkaStreams, KeyValue, StreamsConfig}
import org.apache.kafka.streams.kstream._
import org.apache.kafka.streams.processor.TopologyBuilder.AutoOffsetReset
import org.apache.kafka.streams.state.{KeyValueIterator, QueryableStoreTypes, ReadOnlyKeyValueStore}
import org.mehrofiq.KafkaStreamsInteractiveQueriesApp.{SerdeBuilder, SerdeBuilderImpl}
import org.mehrofiq.SensorHeartbeat
import org.mehrofiq.kafkaMergerApp.ConfluentProperties

import scala.collection.JavaConverters._

object MainApp extends App {

  val confluentProperties = ConfluentProperties
  val properties = confluentProperties.get()

  val streamConfig : StreamsConfig = new StreamsConfig(properties)
  val streamBuilder : KStreamBuilder = new KStreamBuilder()

  val serdeBuilder : SerdeBuilder = new SerdeBuilderImpl()
  val heartbeatSerde = serdeBuilder.specificAvroSerde[SensorHeartbeat](false);

  val localStoreName = "mehrofiq-localStore"

  val mergedStream: KStream[String, SensorHeartbeat] =
    streamBuilder.stream(
      AutoOffsetReset.EARLIEST,
      Serdes.String(),
      heartbeatSerde,
      "Merged-SensorsHeartbeat"
    )

  val mergedStreamTable : KGroupedStream[String, SensorHeartbeat] =
    mergedStream.groupByKey(Serdes.String(), heartbeatSerde)

  val table: KTable[String, SensorHeartbeat] =
    mergedStreamTable.reduce(
      (oldValue: SensorHeartbeat, newValue: SensorHeartbeat) => newValue,
      localStoreName)

  /*val tableStoreName = "mehrofiq-tableStore"

  val mergedHeartbeatTable: KTable[String, SensorHeartbeat] =
    streamBuilder.table[String, SensorHeartbeat](
      AutoOffsetReset.EARLIEST,
      Serdes.String(),
      heartbeatSerde,
      "Merged-SensorsHeartbeat",
      tableStoreName
    )*/

  val countStoreName = "mehrofiq-countStore"

  val countTable: KTable[String, java.lang.Long] =
    mergedStreamTable.count((countStoreName))

  val timeWindowStoreName = "mehrofiq-countStore-timeWindow"
  val countStoreForLastEightHours =
    mergedStreamTable.count(TimeWindows.of(TimeUnit.HOURS.toMillis(8)) , timeWindowStoreName)

  val sessionWindowStoreName = "mehrofiq-countStore-sessionWindow"
  val countForSession =
    mergedStreamTable.count(SessionWindows.`with`(1000) , timeWindowStoreName)


  setupLocalStoreTimer()
  setupCountStoreTimer()
  //setupTableStoreTimer()

  val kafkaStreams = new KafkaStreams(streamBuilder, streamConfig)
  kafkaStreams.start()

  Runtime.getRuntime.addShutdownHook(new Thread(() => {
    kafkaStreams.close()
  }))

  private def setupLocalStoreTimer() : Unit = {
    val timer = new Timer()
    timer.schedule(new TimerTask {
      override def run(): Unit = {

        val store: ReadOnlyKeyValueStore[String, SensorHeartbeat] =
          kafkaStreams.store(
            localStoreName,
            QueryableStoreTypes.keyValueStore[String, SensorHeartbeat]())

        val values: KeyValueIterator[String, SensorHeartbeat] = store.all()

        while(values.hasNext()) {
          val item = values.next()
          println(s"HeartbeatStore: ${item.key} - ${item.value}")
        }

      }
    }, 10000)
  }

  private def setupCountStoreTimer() : Unit = {
    val timer = new Timer()
    timer.schedule(new TimerTask {
      override def run(): Unit = {

        val countStore: ReadOnlyKeyValueStore[String, java.lang.Long] =
          kafkaStreams.store(
            countStoreName,
            QueryableStoreTypes.keyValueStore[String, java.lang.Long]())

        val countValues: KeyValueIterator[String, lang.Long] = countStore.all()

        while (countValues.hasNext()) {
          val item = countValues.next()
          println(s"CountValueStore: ${item.key} - ${item.value}")
        }

      }
    }, 20000)
  }

  /*
  private def setupTableStoreTimer() : Unit = {
    val timer = new Timer()
    timer.schedule(new TimerTask {
      override def run(): Unit = {

        val store: ReadOnlyKeyValueStore[String, SensorHeartbeat] =
          kafkaStreams.store(
            tableStoreName,
            QueryableStoreTypes.keyValueStore[String, SensorHeartbeat]())

        val countValues: KeyValueIterator[String, SensorHeartbeat] = store.all()

        while (countValues.hasNext()) {
          val item = countValues.next()
          println(s"CountValueStore: ${item.key} - ${item.value}")
        }

      }
    }, 30000)
  }*/

}
