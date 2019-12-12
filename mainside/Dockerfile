# hadolint ignore=DL3006
FROM adoptopenjdk/maven-openjdk11

RUN apt update
RUN apt install -y dnsutils
RUN apt install -y net-tools

VOLUME /tmp
COPY . /data/
WORKDIR /data
RUN mvn clean verify -DskipTests=true
WORKDIR /data/mainside

CMD ["sh", "-c", "until nslookup 127.0.0.1; do echo waiting for 127.0.0.1; sleep 2; done; until netstat -tulpn | grep 3306; do echo waiting for port 127.0.0.1:3306; sleep 2; done; echo All right!!!; mvn flyway:clean flyway:migrate spring-boot:run -PkuberSinglePod"]