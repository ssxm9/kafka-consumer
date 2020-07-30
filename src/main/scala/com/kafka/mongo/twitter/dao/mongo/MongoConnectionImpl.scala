package com.kafka.mongo.twitter.dao.mongo
import com.kafka.mongo.twitter.utils.AppConfig
import org.mongodb.scala.MongoClient

import scala.util.{Failure, Success, Try}

class MongoConnectionImpl extends MongoConnection {
  override val mongoClient: Option[MongoClient] = initConnection() match {
    case Left(msg) => None
    case Right(client) => Some(client)
  }

  private def initConnection(): Either[Throwable, MongoClient] = {
    val mongoUrl = AppConfig.mongoUrl
    Try(MongoClient(mongoUrl)) match {
      case Success(mongoClient) => Right(mongoClient)
      case Failure(msg) => Left(msg)
    }
  }
}
