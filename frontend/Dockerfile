#### Stage 2: Build the react application
FROM node as frontend

WORKDIR /frontend

COPY /src/main/frontend .

RUN npm i

RUN npm run build

#### Stage 2: Build the application
FROM openjdk:16-jdk-alpine as service

WORKDIR /app

# Copy maven executable to the image
COPY mvnw .
COPY .mvn .mvn

# Copy the pom.xml file
COPY pom.xml .

# Copy the project source
COPY ./src ./src
COPY ./pom.xml ./pom.xml

COPY --from=frontend /frontend/build ./src/main/resources/static

RUN chmod 755 /app/mvnw

RUN ./mvnw dependency:go-offline -B

RUN ./mvnw package -DskipTests

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/target/frontend-0.0.1-SNAPSHOT.jar"] service