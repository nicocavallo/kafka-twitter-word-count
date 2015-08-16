package example

import play.api.libs.json.{Format, Json}

/**
 * Created by Nicolas on 16/08/2015.
 */
package object model {
  /**
   * Created by Nicolas on 16/08/2015.
   */
  case class Tweet(user:String, text: String) {
    import Tweet._

    def toJson = Json.toJson(this)
  }

  object Tweet {
    implicit val format:Format[Tweet] = Json.format[Tweet]
  }

  case class WordCount(word:String, count:Long)
}
