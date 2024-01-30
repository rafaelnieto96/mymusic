FROM maven:3.8.5-openjdk-17 AS build

WORKDIR /app
COPY . /app

RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim

WORKDIR /app

COPY --from=build /app/target/mymusic-0.0.1-SNAPSHOT.jar mymusic.jar

COPY --from=build /app/uploads /app/uploads
RUN find /app/uploads -type d -exec chmod 0777 {} \;
RUN find /app/uploads -type f -exec chmod 777 {} \;

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "mymusic.jar"]
