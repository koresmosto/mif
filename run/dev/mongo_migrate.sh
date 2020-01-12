#!/usr/bin/env bash
echo "===================================================================="
echo " Migrate MongoDB on local env. (dev)                                "
echo " (It's clearing DB before migration)                                "
echo "===================================================================="

PROJECT_PATH="`dirname \"$0\"`"/../..
MONGO_MIGRATION_SCRIPTS_PATH="${PROJECT_PATH}/intro-service/src/main/resources/db/migration/mongo";

# Clear DB before migration
mongo --port 27017 admin ${MONGO_MIGRATION_SCRIPTS_PATH}/clear/*.js --authenticationDatabase "admin" -u "root" -p "rootpass"

# Migrate
mongo --port 27017 admin ${MONGO_MIGRATION_SCRIPTS_PATH}/init/*.js --authenticationDatabase "admin" -u "root" -p "rootpass"
