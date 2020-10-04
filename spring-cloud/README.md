# Spring Boot and spring cloud sample with eureka server and eureka client.  
### REST end point for now only sysout(method) or return static students object

A minimal, RESTFUL api for Spring Cloud. This project includes eureka server with microservices configured as eureka client along with api-gateway right out of the box!

 
## Microservices
| Name | Url | Expected Output |
| ------ | ------ | ------ |
| eureka-server | `http://host:8761/` | Displays eureka server homepage |


## Prometheus and Grafana for monitoring

### Running prometheus in the docker container.

```
>docker run -p 9090:9090 -v C:\Users\91960\Projects\springPlayground\spring-cloud\order-service\src\main\resources\
prometheus.yml prom/prometheus
``` 

### Running grafana in docker container.

```
>docker run -d --name=grafana -p 3000:3000 grafana/grafana
``` 


