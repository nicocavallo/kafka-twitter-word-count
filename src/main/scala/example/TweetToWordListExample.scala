package example

import example.consumer.SingleTopicConsumer
import example.model.Tweet
import example.processor.TweetToWordList
import example.producer.Producer
import play.api.libs.json.Json

/**
 * Created by Nicolas on 16/08/2015.
 */
object TweetToWordListExample extends App {

  def getTopics(args: Array[String]):(String,String) = args.size match {
    case 0 => ("tweetTopic","wordTopic")
    case 1 => (args(0),"wordTopic")
    case _ => (args(0),args(1))
  }

  val (sourceTopic,destinationTopic) = getTopics(args)
  
  val tweetConsumer = SingleTopicConsumer(sourceTopic)
  val wordProducer = Producer[String](destinationTopic)
  
  tweetConsumer.read.foreach { m =>
    val tweet:Tweet = Json.fromJson[Tweet](Json.parse(m)).get
    val words = TweetToWordList.process(tweet)
    words foreach wordProducer.send
  }
  

}
