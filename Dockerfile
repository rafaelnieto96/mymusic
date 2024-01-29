FROM maven:3.8.5-openjdk-17 AS build
RUN mkdir -p /app/uploads
RUN find /app/uploads -type d -exec chmod 0777 {} \;
RUN find /app/uploads -type f -exec chmod 777 {} \;

WORKDIR /app
COPY . /app

RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim

WORKDIR /app

COPY --from=build /app/target/mymusic-0.0.1-SNAPSHOT.jar mymusic.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "mymusic.jar"]
