# Spring Boot Sample Projects
[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.org/joemccann/dillinger)

This repo contains sample mvn spring-boot projects for:-

  - [spring-mongodb](https://github.com/shubhamlatkar/springPlayground/tree/master/spring-mongodb) contains Spring-Bot JPA and Mongo Repoitory.
  - [spring-h2-sample](https://github.com/shubhamlatkar/springPlayground/tree/master/spring-h2-sample) H2 in memory DB sample with spring boot .
  - [spring-security](https://github.com/shubhamlatkar/springPlayground/tree/master/spring-security) Spring security with in memory users and Http basic auth for REST Api's.
  - [spring-DBAuth](https://github.com/shubhamlatkar/springPlayground/tree/master/security-DBAuth) Spring security with DB integreated Http basic auth for REST Apis's.
  - [spring-DBAuth-jwt](https://github.com/shubhamlatkar/springPlayground/tree/master/security-DBAuth-jwt) Spring security with DB integreated JWT token auth for REST Apis's.
  - [spring-cloud](https://github.com/shubhamlatkar/springPlayground/tree/master/spring-cloud) Sample spring boot micorservices with eureka server and eireka client with api-gateway. 

### [Spring Cloud](https://github.com/shubhamlatkar/springPlayground/tree/master/spring-cloud)
##### Sample Eureka server with eureka clients micoroservices and hystrix dashboard for fallback and monitoring:-
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