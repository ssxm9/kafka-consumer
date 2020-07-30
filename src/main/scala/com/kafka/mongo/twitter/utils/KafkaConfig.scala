package com.kafka.mongo.twitter.utils

import java.util.Properties
import java.util.concurrent.TimeUnit
import com.kafka.mongo.twitter.utils.AppConfig._

object KafkaConfig {
  def getKafkaProperties(kafkaProp:String): Properties = {
    //TODO - There could be some changes here based on architecture
    val props = new Properties()
    val brokers = conf.getString(kafkaProp+".brokers")
    val autoCommit = conf.getString(kafkaProp+".autoCommit")
    val maxPollRecords = conf.getString(kafkaProp+".maxPollRecords")
    val pollTimeout=
      TimeUnit.MINUTES.toMillis(conf.getLong(kafkaProp+".pollTimeoutInMin"))
    val groupId = conf.getString(kafkaProp+".groupId")
    val offsetReset = conf.getString(kafkaProp+".offsetReset")

    props.put("bootstrap.servers", brokers)
    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("group.id", groupId)
    props.put("enable.auto.commit", autoCommit)
    props.put("auto.offset.reset", offsetReset)
    props.put("max.poll.records",maxPollRecords)
    props
  }
}
