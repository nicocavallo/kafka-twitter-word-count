libraryDependencies ++= Seq(
  "org.apache.kafka" %% "kafka" % "0.8.1"
      exclude("javax.jms", "jms")
      exclude("com.sun.jdmk", "jmxtools")
      exclude("com.sun.jmx", "jmxri"),
      "com.typesafe" % "config" % "1.2.1",
      "com.typesafe.play" %% "play-json" % "2.4.0",
      "org.scalatest" %% "scalatest" % "2.2.1" % "test",
      "org.twitter4j" % "twitter4j-core" % "4.0.2",
      "org.twitter4j" % "twitter4j-stream" % "4.0.2"
)

fork in run := true