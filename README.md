#Kafka Twitter Word Count
## Introduction
This is a Scala project to shown an example of Apache Kafka usage.
This example illustrates a Technical Talk in [Piscolve](http://www.picsolve.biz/). [Slides](https://www.dropbox.com/s/knamkw9z8g1efqg/Kafka%20Presentation.pptx?dl=0) are also available.
### About the example
This project tends to count the most used words in [twitter](http://twitter.com).
It is based on activator project [hello-kafka](https://github.com/vngrs/activator-hello-kafka)

The app should perform three main tasks:

1. Read tweets from Twitter API
2. Split those tweets in words
3. Count the words

Instead of creating a Gigantic Application to perform those three operations, we will split our program in three different programs connected via [Apache Kafka](http://kafka.apache.org/).
#### The flow
![Kafka Twitter Word Count Flow](https://dl.dropboxusercontent.com/s/lkbsnlj7pgc7pux/kafka_twitter_word_count_example.png?dl=0)

#### The tools

1. [Sbt](http://www.scala-sbt.org/)
2. [TwitterJ](http://twitter4j.org/en/index.html)
3. [Apache Kafka](https://www.apache.org/dyn/closer.cgi?path=/kafka/0.8.2.0/kafka_2.10-0.8.2.0.tgz)

## Launching the application
#### Clone this repository
```
> git clone git@github.com:nicocavallo/kafka-twitter-word-count.git
```
#### 

### Download and install Apache Kafka
#### [Download Apache Kafka](https://www.apache.org/dyn/closer.cgi?path=/kafka/0.8.2.0/kafka_2.10-0.8.2.0.tgz)
#### Un-tar the file

```
> tar -xzf kafka_2.10-0.8.2.0.tgz
> cd kafka_2.10-0.8.2.0
```
### Start Server
#### Start Zookeeper

```
> bin/zookeeper-server-start.sh config/zookeeper.properties 
[2015-08-16 16:00:47,768] INFO Reading configuration from: config/zookeeper.properties (org.apache.zookeeper.server.quorum.QuorumPeerConfig)
...
```
#### Start Kafka Server

```
> bin/kafka-server-start.sh config/server.properties
[2015-08-16 16:02:03,032] INFO Verifying properties (kafka.utils.VerifiableProperties)
[2015-08-16 16:02:03,055] INFO Property socket.send.buffer.bytes is overridden to 1048576 (kafka.utils.VerifiableProperties)
...
```
### Start Producers and Consumers
#### Go to the project you've just clone

```
> sbt 'runMain example.TwitterProducerExample'
```
#### If you want to see what this class is producing, you can go to kafka's installation directory and run console client like this:

```
> bin/kafka-console-consumer.sh --zookeeper localhost:2181 --topic tweetTopic
```
#### You should see something like this:

```
{
  "user" : "LayzaCristine_",
  "text" : "RT @AniinhaaCouto: @LayzaCristine_ http://t.co/9WmtZJotEv"
}
{
  "user" : "giuliocampese",
  "text" : "RT @Lucy_822: @Storace 😂😂😂👍🏻 http://t.co/bx8RLiJiv0"
}
{
  "user" : "okebgt01",
  "text" : "TrendieID: \"Ramires\" shows up as trending topic in Indonesia at rank 8"
}
{
  "user" : "DannyO_92",
  "text" : "When yaya and silva play city are world class"
}
{
  "user" : "anddre_lv",
  "text" : "@Feriscar @celiadsf me he perdido ya en la conversación cabeza alpaca"
}
^CConsumed 97 messages
```
#### Now that we are sure our producer is working, we can go to the project's directory again, and in a new terminal, run our next process, the one that is going to split tweets into words

```
sbt 'runMain example.TweetToWordListExample'
```
#### As we did before, we can go to Apache console and see what is happening with this new processor:

```
> bin/kafka-console-consumer.sh --zookeeper localhost:2181 --topic wordTopic
```
#### Finally, we will count the words and print them by executing the following command:

```
sbt 'runMain example.WordCountExample'
```
#### After a couple of seconds you will start seeing the output of  the whole process:

```
...
background log: info: WordCount(is,10)
background log: info: WordCount(that,4)
background log: info: WordCount(it,6)
background log: info: WordCount(costs,1)
background log: info: WordCount(mil,2)
background log: info: WordCount(to,26)
background log: info: WordCount(send,2)
background log: info: WordCount(to,27)
background log: info: WordCount(the,14)
background log: info: WordCount(of,9)
background log: info: WordCount(the,15)
background log: info: WordCount(office,1)
...
```

### Improvements
* Add logging
* Refactor to add more tests
* Migrate to Actors Model
* Create new examples