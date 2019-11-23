#!/usr/bin/env bash
# Before it migrate mysql db in kubernetes cluster:
# $ m clean process-resources flyway:clean flyway:migrate -Dflyway.url=jdbc:mysql://localhost:3406/makeitfinemysqlkuber -Dflyway.user=makeitfineuser -Dflyway.password=makeitfinepass -Dflyway.locations="filesystem:src/main/resources/db/migration/mysql"
#
kubectl delete service appside
kubectl delete deployments.apps appside
docker rmi appside:latest
cd ~/dev/projects/makeitfine/ && mvn clean install -DskipTests=true -Pkuber
cd ~/dev/projects/makeitfine/ && cd mainside/ && docker build --file=Dockerfile --tag=appside:latest --rm=true .
cd ~/dev/projects/makeitfine/ && kubectl create -f running/kubernetes/app-deployment.yaml

sleep 15s
kubectl describe pod demo-frontend- | grep "Container ID"
# see pod on kuber:
# k describe pods app- <tab>
# d logs <inser>