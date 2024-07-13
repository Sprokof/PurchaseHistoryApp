FROM openjdk:21
MAINTAINER parmenid
WORKDIR /app
COPY ./target/history-1.0-SNAPSHOT-spring-boot.jar /app/history-app-1.0.0.jar
ENTRYPOINT ["java","-jar","/app/history-app-1.0.0.jar"]