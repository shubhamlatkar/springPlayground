#### Stage 1: Build the application
FROM openjdk:16-jdk-alpine as h2auth

WORKDIR /app

# Copy maven executable to the image
COPY mvnw .
COPY .mvn .mvn

# Copy the pom.xml file
COPY pom.xml .

# Copy the project source
COPY ./src ./src
COPY ./pom.xml ./pom.xml

RUN chmod 755 /app/mvnw

RUN ./mvnw dependency:go-offline -B

RUN ./mvnw package -DskipTests

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/target/securityWithJwtH2-0.0.1-SNAPSHOT.jar"]