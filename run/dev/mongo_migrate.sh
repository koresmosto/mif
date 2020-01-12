#!/usr/bin/env bash
echo "===================================================================="
echo " Migrate MongoDB on local env. (dev)                                "
echo "===================================================================="

PROJECT_PATH="`dirname \"$0\"`"/../..
MONGO_MIGRATION_SCRIPTS_PATH="${PROJECT_PATH}/run/mongodb/resources/db-migrate";

mongo --port 27017 admin ${MONGO_MIGRATION_SCRIPTS_PATH}/*.js --authenticationDatabase "admin" -u "root" -p "rootpass"
