package com.kafka.mongo.twitter.consumer

import java.time.Duration
import java.util
import java.util.Properties

import com.kafka.mongo.twitter.schemas.Schemas.TweetConsumerMessage
import com.kafka.mongo.twitter.dao.mongo.TwitterDaoImpl._

import scala.collection.JavaConverters.iterableAsScalaIterableConverter
import com.kafka.mongo.twitter.utils.KafkaConfig
import org.apache.kafka.clients.consumer.{ConsumerRecords, KafkaConsumer}
import org.apache.log4j.Logger
import com.kafka.mongo.twitter.utils.AppConfig._
import org.apache.kafka.common.errors.WakeupException

class TwitterConsumer {
  val logger: Logger = Logger.getLogger(this.getClass)
  val kafkaProp = "kafka-mongo-topic-twitter"
  val prop: Properties = KafkaConfig.getKafkaProperties(kafkaProp)
  val twitterConsumer = new KafkaConsumer[String,String](prop)
  twitterConsumer.subscribe(util.Arrays.asList(conf.getString(kafkaProp+ ".topic")))

  def consumeFromKafkaTopic(): Unit = {
    try{
      while(true){
        val tweetRecords: ConsumerRecords[String, String] = twitterConsumer.poll(Duration.ofMillis
        (conf.getInt(kafkaProp+ ".pollTimeoutInMin")))
        logger.debug("Consumer is up and running")
        if(tweetRecords.asScala.nonEmpty)
          tweetRecords.asScala.foreach(record => {
            logger.info(record.value())
            insertToMongo(TweetConsumerMessage(record.value()))
          })
        if(tweetRecords.asScala.nonEmpty)
          twitterConsumer.commitSync()
      }
    }catch{
      case e : WakeupException =>
        logger.error(e.printStackTrace())
        sys.exit(2)
    }
  }

  def insertToMongo(data: TweetConsumerMessage): Unit = {
    insertIntoMongo(data)
  }
}
