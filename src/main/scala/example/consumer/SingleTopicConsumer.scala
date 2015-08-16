package example.consumer

import kafka.consumer.{Consumer => KafkaConsumer, ConsumerIterator, Whitelist}
import kafka.serializer.{DefaultDecoder, Decoder}
import scala.collection.JavaConversions._

case class SingleTopicConsumer(topic: String) extends Consumer(List(topic)) {
  private lazy val consumer = KafkaConsumer.create(config)
  val threadNum = 1

  private lazy val consumerMap = consumer.createMessageStreams(Map(topic -> threadNum))
  private lazy val stream = consumerMap.getOrElse(topic, List()).head

  override def read(): Stream[String] = Stream.cons(new String(stream.head.message()), read())
}