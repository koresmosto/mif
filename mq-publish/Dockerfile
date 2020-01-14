FROM openjdk:11-jre
MAINTAINER Kores Mosto <koresmosto@gmail.com>

VOLUME /tmp

COPY target/mq-publish-1.0-SNAPSHOT.war /app.war

CMD ["java", "-jar", "-Dspring.profiles.active=docker", "/app.war"]
