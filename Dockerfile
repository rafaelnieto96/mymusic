FROM maven:3.8.5-openjdk-17 AS build

RUN mkdir -p /app/uploads
RUN find /app/uploads -type d -exec chmod 0777 {} \;
RUN find /app/uploads -type f -exec chmod 777 {} \;
WORKDIR /app

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} mymusic.jar

EXPOSE 8080