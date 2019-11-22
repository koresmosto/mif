#Run in folder "mainside" under minikube VM
#$ docker build --file=Dockerfile --tag=mainside:latest --rm=true .
#Mount project to minikube:
#$ minikube mount practice:/home/docker/projects
FROM adoptopenjdk/maven-openjdk11
VOLUME /tmp
COPY target/mainside-1.0-SNAPSHOT.war app.jar
ENV JAVA_OPTS=""
ENTRYPOINT exec java -jar /app.jar --debug
