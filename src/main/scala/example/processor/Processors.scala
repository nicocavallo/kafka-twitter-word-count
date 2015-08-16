package example.processor

import example.model.{WordCount, Tweet}

import scala.collection.mutable

/**
 * Created by Nicolas on 16/08/2015.
 */
trait Processor[F,T] {

  def process(message: F): T

}

object TweetToWordList extends Processor[Tweet,List[String]] {
  override def process(message: Tweet): List[String] = message.text.split(" ").toList
}

object WordToWordCount extends Processor[String,WordCount] {

  //This map acts as a DB where WordCounts should be stored.
  val countByWord = mutable.Map[String,Long]().withDefaultValue(0L)

  override def process(message: String): WordCount = {
    val wordCount = WordCount(message,countByWord(message) + 1)
    countByWord += wordCount.word -> wordCount.count
    wordCount
  }
}

object Printer extends Processor[WordCount,Unit] {
  override def process(message: WordCount): Unit = println(message)
}