package example

import example.consumer.SingleTopicConsumer
import example.processor.WordToWordCount

/**
 * Created by Nicolas on 16/08/2015.
 */
object WordCountExample extends App {

  val arguments = args :+ "wordTopic"

  val wordConsumer = SingleTopicConsumer(arguments(0))

  wordConsumer.read.foreach { w =>
    val wordCount = WordToWordCount.process(w)
    println(wordCount)
  }

}
