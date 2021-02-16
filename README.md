# Spring Boot Sample Projects
[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.io/#https://github.com/shubhamlatkar/mentor-on-demand-backend)
[![Spring Spring-Boot](https://img.shields.io/badge/Spring-Spring_boot-green?logo=spring)](https://start.spring.io/)
[![MongoDb Database](https://img.shields.io/badge/MongoDB-Database-47A248?logo=mongodb)](https://cloud.mongodb.com/)
[![Intellij Intellij-idea](https://img.shields.io/badge/Intellij-Intellij_idea-black?logo=intellij-idea)](https://www.jetbrains.com/idea/)


This repo contains sample mvn spring-boot projects for:-

  - [spring-mongodb](https://github.com/shubhamlatkar/springPlayground/tree/master/spring-mongodb) contains Spring-Bot JPA and Mongo Repository.
  - [spring-h2-sample](https://github.com/shubhamlatkar/springPlayground/tree/master/spring-h2-sample) H2 in memory DB sample with spring boot .
  - [spring-security](https://github.com/shubhamlatkar/springPlayground/tree/master/spring-security) Spring security with in memory users and Http basic auth for REST Api's.
  - [spring-DBAuth](https://github.com/shubhamlatkar/springPlayground/tree/master/security-DBAuth) Spring security with DB integrated Http basic auth for REST Apis's.
  - [spring-DBAuth-jwt](https://github.com/shubhamlatkar/springPlayground/tree/master/security-DBAuth-jwt) Spring security with DB integrated JWT token auth for REST Apis's.
  - [spring-cloud](https://github.com/shubhamlatkar/springPlayground/tree/master/spring-cloud) Sample spring boot microservices with eureka server and eureka client with api-gateway.
  - [spring-kafka](https://github.com/shubhamlatkar/springPlayground/tree/master/kafka) Spring Cloud event driven example using kafka asa event bus.   
  - [spring-boot-gradle](https://github.com/shubhamlatkar/springPlayground/tree/master/user-service) Sample spring boot project with gradle.   
  - [config](https://github.com/shubhamlatkar/springPlayground/tree/master/config) Spring configurations folder.
  - [ELK-Stack](https://github.com/shubhamlatkar/springPlayground/tree/master/ELK) ELK stack for logs.
  - [Logging](https://github.com/shubhamlatkar/springPlayground/tree/master/logging) Spring boot logging sample.
  
### [Spring-boot-gradle](https://github.com/shubhamlatkar/springPlayground/tree/master/user-service)
###### Sample spring boot project with gradle:-

### [Spring-Kafka](https://github.com/shubhamlatkar/springPlayground/tree/master/kafka)
##### Sample For spring cloud streams using apache kafka as event bus:-
* [kafka-server](https://github.com/shubhamlatkar/springPlayground/tree/master/kafka/kafka-server) Kafka and zookeeper docker-compose container.
* [kafka-consumer](https://github.com/shubhamlatkar/springPlayground/tree/master/kafka/kafka-consumer) Spring cloud streams cloud consumer consuming messages on the topic from kafka.
* [kafka-consumer](https://github.com/shubhamlatkar/springPlayground/tree/master/kafka/kafka-consumer) Spring cloud streams cloud producer producing messages on the topic to kafka.
    
### [Spring Cloud](https://github.com/shubhamlatkar/springPlayground/tree/master/spring-cloud)
##### Sample Eureka server with eureka clients microservices and hystrix dashboard for the fallback and monitoring:-
* [eureka-server](https://github.com/shubhamlatkar/springPlayground/tree/master/spring-cloud/eureka-server) Eureka server for the microservices.
* [api-gateway](https://github.com/shubhamlatkar/springPlayground/tree/master/spring-cloud/api-gateway) Api gateway for Users and Orders service.
* [hystrix-dashboard](https://github.com/shubhamlatkar/springPlayground/tree/master/spring-cloud/hystrixDashboard) Hystrix dashboard for monitoring services.
* [user-service](https://github.com/shubhamlatkar/springPlayground/tree/master/spring-cloud/user-service) User-service for managing users.
* [order-service](https://github.com/shubhamlatkar/springPlayground/tree/master/spring-cloud/order-service) Order-service for managing orders.
* [event-bus](https://github.com/shubhamlatkar/springPlayground/tree/master/spring-cloud/event-bus) event-bus for communicating between services.

### [Spring Boot MongoDB](https://github.com/shubhamlatkar/springPlayground/tree/master/spring-mongodb) 
##### Sample CURD operations with MongoRepository for:-
* Users Documenet
* Roles Document
* Authorities Document

### [Spring Boot H2 Sample](https://github.com/shubhamlatkar/springPlayground/tree/master/spring-h2-sample)
###### Spring-boot h2 in memory persistant DB sample for:
* User Table
* Role Table
* Authorities Table

### [Spring Boot security in memory http auth](https://github.com/shubhamlatkar/springPlayground/tree/master/spring-cloud/order-service)
###### Spring security with in memory authentication with basic http auth:-
* In memory user with fixed roles and credential's for authentication with basic http auth.

### [Spring Boot security with DB auth with Basic Http auth](https://github.com/shubhamlatkar/springPlayground/tree/master/security-DBAuth)
###### Spring boot with mongo db authentication with Basic Http auth includes:-
* Users with credential's authentication from mongo DB.
* Roles based route authentication.
* Permission based route authentication.

### [Spring Boot security with DB auth with JWT token authentication](https://github.com/shubhamlatkar/springPlayground/tree/master/security-DBAuth-jwt)
##### Spring boot with mongo db authentication with JWT token Authentication for the routes. It includes:-
* Users with credential's authentication from mongo DB.
* Login and signup route open to all.
* Token based authentication for all the remaining routes.
