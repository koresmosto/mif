#!/usr/bin/env bash
echo "===================================================================="
echo " Kill processes on ports: 8080, 8081, 8082, 8083, 8084              "
echo "===================================================================="

sudo fuser -k 8080/tcp
sudo fuser -k 8081/tcp
sudo fuser -k 8082/tcp
sudo fuser -k 8083/tcp
sudo fuser -k 8084/tcp
