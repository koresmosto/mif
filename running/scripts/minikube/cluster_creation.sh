#!/usr/bin/env bash
#It's good to make `mvn clean install` or `mvn clean install -DskipTests=true` on project before
echo "=================================================================="
echo "=================================================================="
echo " Create Minikube VM Cluster                                       "
echo "=================================================================="
echo
#Question if proceed
read -e -p "This script will REMOVE Minikube VM and create it newly. Proceed ? [Y/n] " YN

if [[ $YN != "y" && $YN != "Y" ]]
then
  exit 0;
fi

minikube stop
minikube delete

minikube config set cpus 2
minikube config set memory 6144
minikube config set disk-size 20g
minikube addons enable dashboard

echo "Minikube configs:"
minikube config view

minikube start

minikube ssh <<'ENDSSH'
echo -e "docker\ndocker" | sudo passwd docker
echo "source ~/.bashrc" > .profile
exit
ENDSSH

ssh-keygen -R $(minikube ip)

scp -o StrictHostKeyChecking=no -i $(minikube ssh-key) $(dirname $0)/.bashrc docker@$(minikube ip):/home/docker/

#Configuration forwarding in VirtualBox
VBoxManage controlvm minikube natpf1 mysql-3406,tcp,127.0.0.1,3406,,30306
VBoxManage controlvm minikube natpf1 appside-8180,tcp,127.0.0.1,8180,,30080
