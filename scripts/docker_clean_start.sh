#!/usr/bin/env bash
#
# Main docker start script
#
echo "=========================================================="
echo " Remove docker containers, create new ones and start them"
echo "=========================================================="
echo ""
docker stop mys j8m
docker rm mys j8m
docker network rm makeitfine_mifnet_back
docker-compose up -d
#wait_time=2s
#echo "sleep for $wait_time while maven repos download executes"
#sleep $wait_time
#docker exec -it j8m bash '/data/output.sh'
