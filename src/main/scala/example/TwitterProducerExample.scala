package example

import example.producer.TwitterProducer

/**
 * Created by Nicolas on 16/08/2015.
 */
object TwitterProducerExample extends App {
  val arguments:Array[String] = args :+ "tweetTopic"

  val twitterProducer = new TwitterProducer(arguments(0))

  twitterProducer.run
}
