#!/usr/bin/env bash

BASE_DIR=`pwd`
LOG_DIR=$HOME'/Horizon-HomePage-logs'
Horizon_HomePage_service="Horizon-HomePage-service"
if [ ! -d ${LOG_DIR} ];
    then mkdir -p ${LOG_DIR}
fi

export D_SKIPTEST='-DskipTests=true'
export LOG_PREFIX="\n\033[1m[Horizon-Homepage]\033[0m[`date -u`]\t"
LOG() {
  echo -e "${LOG_PREFIX}$1\n"
}

LOG 'pull from git start'
git pull origin master
LOG 'pull from git end'

LOG 'build start'
bash ./mvnw clean package ${D_SKIPTEST}
LOG 'build success'

LOG 'kill old service'
HORIZON_PID="`ps -ef | grep Horizon | grep -v "grep" | awk -F ' ' '{print $2}'`"

if [ ! -z ${HORIZON_PID} ];
    then
        LOG "killing service"
        LOG "${Horizon_HomePage_service} pid are: ${HORIZON_PID}"
        kill -9 ${HORIZON_PID} && LOG "killing ${Horizon_HomePage_service} successfully"
fi

LOG 'start service'
cd Horizon-HomePage-service/target
nohup java -jar Horizon-HomePage-service-1.0.snapshot.jar>${LOG_DIR}/Horizon-HomePage-service.log &

LOG "started !!"
exit 0
