#Run in folder "mainside" under minikube VM
#$ docker build --file=Dockerfile --tag=mainside:latest --rm=true .
#Mount project to minikube:
#$ minikube mount practice:/home/docker/projects
FROM adoptopenjdk/openjdk11:jdk-11.0.4_11-alpine
VOLUME /tmp
COPY target/mainside-1.0-SNAPSHOT.war app.war
ENV JAVA_OPTS=""

CMD ["sh", "-c", "ping -c 3 172.17.0.12; ping -c 3 172.17.0.10; exec java -jar -Dspring.profiles.active=kuber,config_mysql /app.war"]
