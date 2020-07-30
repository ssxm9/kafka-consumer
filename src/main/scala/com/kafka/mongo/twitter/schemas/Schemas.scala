package com.kafka.mongo.twitter.schemas

object Schemas {
  case class Tweet(userId: Int, source: String)
  case class TweetConsumerMessage(data: String)
}
