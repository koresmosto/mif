#!/usr/bin/env bash
#It's good to make `mvn clean install` or `mvn clean install -DskipTests=true` on project before
echo "=================================================================="
echo "=================================================================="
echo " Remove kubernetes services, deployments and recreate them again  "
echo " (service and deployments: mysql and appside shouldn't exist)     "
echo "=================================================================="
echo
echo

eval $(minikube docker-env)

echo "=================================================================="
echo " All service && deployment (Mysql and Appside) deletion           "
echo "=================================================================="

kubectl delete service all
kubectl delete deployments.apps all

echo "=================================================================="
echo " All service && deployment (Mysql and Appside) creation           "
echo "=================================================================="

cd ~/dev/projects/makeitfine
docker build --file=mainside/Dockerfile --tag=appside:latest --rm=true .

cd ~/dev/projects/makeitfine
kubectl create -f running/kubernetes/all-deployment.yaml

echo "Sleep for 30s for all containers successfully creation"
sleep 30s # downloading maven dependencies

echo "=================================================================="
echo " All application pods Container IDs:                                        "
echo " (Use command: $> docker logs <Container ID>)                     "
echo "=================================================================="
kubectl describe pod all- | grep "Container ID"

## Mount host directory:
#  $> minikube mount .:/home/docker/projects
## Copy files from localhost machine to minikube VM:
#  Read article: https://stackoverflow.com/questions/46086303/how-to-transfer-files-between-local-machine-and-minikube
#  Read article: https://www.ostechnix.com/fix-ecdsa-host-key-warning-error-arch-linux/
#  $> scp -i $(minikube ssh-key) /home/joe/Downloads/bashrc  docker@$(minikube ip):/home/docker
