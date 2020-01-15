#!/usr/bin/env bash
echo "===================================================================="
echo " Kill processes on ports: 8080, 8081                                "
echo "===================================================================="

sudo fuser -k 8080/tcp
sudo fuser -k 8081/tcp
sudo fuser -k 8082/tcp
sudo fuser -k 8083/tcp
