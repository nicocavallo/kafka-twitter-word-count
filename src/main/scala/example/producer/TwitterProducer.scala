package example.producer

import example.model.Tweet
import twitter4j.Status
import play.api.libs.json.{Json}
import example.utils.TwitterClient

case class TwitterProducer(topicName: String) {

  def run = {

    val strProducer = Producer[String](topicName)
    val twitterClient = new TwitterClient


    def handler(status: Status) = {
      val tweet = Tweet(status.getUser.getScreenName, status.getText)

      strProducer.send(Json.prettyPrint(tweet.toJson))
    }

    twitterClient.addListener(handler)
    twitterClient.run
  }

}
