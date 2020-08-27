# Kafka and zookeeper using docker-compose.

### Thanks to [wurstmeister](https://hub.docker.com/u/wurstmeister) for the kafka and zookeeper images.
#### Thanks [selftuts](http://selftuts.com/kafaka-setup-using-docker-compose/) for the tutorial.

###### Prerequisite:-
* You need to have Docker on you machine and its basic knowledge.
* Also, you will need Docker-compose installed.
* Also, same basic knowledge about kafka and event driven architecture.

###### Steps to run:-
* Run the docker-compose file ```docker-compose -f docker-compose.yml up``` 
* Start the docker-compose container.
* Create the topic of kafkaExample.
* Run producer hit appropriate end point to post.
* Run the consumer to consume the message. 