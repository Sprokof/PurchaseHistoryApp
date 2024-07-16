FROM openjdk:21
MAINTAINER parmenid
WORKDIR /app
COPY ./target/operations-1.0-SNAPSHOT-spring-boot.jar /app/operations-app-1.0.0.jar
ENTRYPOINT ["java","-jar","/app/operations-app-1.0.0.jar"]