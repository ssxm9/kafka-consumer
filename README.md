# Kafka Consumer Application
Read data from Kafka and store it in to MongoDB

## Prerequisites
* Kafka and zookeeper installation
* MongoDB setup

### Configuration
* Open config file -
```
  vi src/main/resources/application.conf
```
* Provide Kafka and MongoDB details

### Execution
After the setup, create a JAR file - 
```
  mvn clean package
```
* Now start zookeeper, kafka and create a topic with the name mentioned in application.conf file

Run the JAR to have kafka consumer up and running. We are ready to consume data
````
 java -jar target/kafka-mongo-application-1.0-SNAPSHOT-jar-with-dependencies.jar
````

* Run the producer and produce messages

### Verify
* Login to mongodb and check if we have the data present 

Login - 
```
  mongo -u <username> -p --authenticationDatabase <db_name>
```
It will prompt for password. Enter the password

Lists all the database - 
```
  show dbs
```

Select the database - 
```
  use <db_name>
```
See collections - 
```
  show collections
```
See the stored data - 
```
  db.<collection_name>.find()
```

Check if the new data is getting inserted - 
```
  db.<collection_name>.count()
```
The count will increase with each new data being inserted to mongodb

