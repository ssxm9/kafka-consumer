package com.kafka.mongo.twitter.launcher

import com.kafka.mongo.twitter.consumer.TwitterConsumer

object AppLaunch {
  def main(args: Array[String]): Unit = {
    new TwitterConsumer().consumeFromKafkaTopic()
  }
}
