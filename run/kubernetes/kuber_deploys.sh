#!/usr/bin/env bash
#It's good to make `mvn clean install` or `mvn clean install -DskipTests=true` on project before
echo "=================================================================="
echo "=================================================================="
echo " Remove kubernetes services, deployments and recreate them again  "
echo "=================================================================="
echo
echo

eval $(minikube docker-env)

echo "=================================================================="
echo " Mysql                                                            "
echo "=================================================================="

kubectl delete service mysql
kubectl delete deployments.apps mysql

cd ~/dev/projects/makeitfine/
kubectl create -f run/kubernetes/mysql-deployment.yaml

sleep 15s

cd mainside/
#mvn flyway:clean flyway:migrate -Dflyway.url=jdbc:mysql://localhost:3406/makeitfinemysqlkuber -Dflyway.user=makeitfineuser -Dflyway.password=makeitfinepass -Dflyway.locations="filesystem:src/main/resources/db/migration/mysql"

echo "=================================================================="
echo " Appside (makeitfine/mainside)                                    "
echo "=================================================================="

kubectl delete service appside
kubectl delete deployments.apps appside
docker rmi appside:latest

cd ~/dev/projects/makeitfine
docker build --file=mainside/Dockerfile --tag=appside:latest --rm=true .

cd ~/dev/projects/makeitfine/
kubectl create -f run/kubernetes/app-deployment.yaml

sleep 15s # downloading maven dependencies

echo "=================================================================="
echo " Mysql & Appside pods Container IDs:                                        "
echo " (Use command: $> docker logs <Container ID>)                     "
echo "=================================================================="
echo " Mysql Container IDs:                                             "
kubectl describe pod mysql- | grep "Container ID"
echo " Appside Container IDs:                                           "
kubectl describe pod appside- | grep "Container ID"