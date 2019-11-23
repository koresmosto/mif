FROM adoptopenjdk/maven-openjdk11
VOLUME /tmp
COPY . /data/
WORKDIR /data
RUN mvn clean verify -DskipTests=true
WORKDIR /data/mainside
ENTRYPOINT exec mvn flyway:clean flyway:migrate spring-boot:run -Pkuber

##
# Build app (run from project root "makeitfine"):
# $> docker build --file=mainside/Dockerfile --tag=appimage:latest --rm=true .
# Run app:
# $> docker run -it --net="host" --name app appimage