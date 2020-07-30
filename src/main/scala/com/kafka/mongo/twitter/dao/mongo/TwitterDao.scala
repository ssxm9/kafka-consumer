package com.kafka.mongo.twitter.dao.mongo

import com.kafka.mongo.twitter.schemas.Schemas.TweetConsumerMessage

trait TwitterDao {
  def insertIntoMongo(record: TweetConsumerMessage): Unit
}
