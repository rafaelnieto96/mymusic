FROM maven:3.8.5-openjdk-17 AS build

RUN mkdir -p /app/uploads
RUN find /app/uploads -type d -exec chmod 0777 {} \;
RUN find /app/uploads -type f -exec chmod 777 {} \;

WORKDIR /app

COPY . .

RUN mvn clean package

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} /app/mymusic.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "mymusic.jar"]
