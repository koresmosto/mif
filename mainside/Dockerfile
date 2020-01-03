FROM openjdk:11-jre
MAINTAINER Kores Mosto <koresmosto@gmail.com>

VOLUME /tmp

# Preparing for waiting of flyway migration completes
RUN apt update
RUN apt install -y dnsutils
RUN apt install -y net-tools
COPY target/classes/dockerize/wait-for-flyway-migration-completes.sh /waiting.sh
RUN chmod +x /waiting.sh

COPY target/mainside-1.0-SNAPSHOT.war /app.war

CMD ["sh", "-c", "./waiting.sh; java -jar -Dspring.profiles.active=docker /app.war"]
