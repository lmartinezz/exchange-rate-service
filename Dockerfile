FROM openjdk:11

MAINTAINER lmartinez

ADD exchange-rate-service-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

EXPOSE 8081