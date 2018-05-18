#!/usr/bin/env bash
#
# Main docker start script
#
echo "=========================="
echo " Remove docker containers"
echo "=========================="
echo ""
docker stop mys j8m
docker rm mys j8m
docker network rm makeitfine_mifnet_back
