FROM openjdk:17-jdk-slim-buster

WORKDIR /app

COPY target/mymusic-0.0.1-SNAPSHOT.jar /app/mymusic.jar

CMD ["java", "-jar", "mymusic.jar"]
