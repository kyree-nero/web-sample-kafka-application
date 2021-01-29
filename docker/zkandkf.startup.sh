#!/bin/bash

# Start the first process
/usr/local/kafka/bin/zookeeper-server-start.sh config/zookeeper.properties & 

status=$?
if [ $status -ne 0 ]; then
  echo "Failed to start process: $status"
  exit $status
fi



# Naive check runs checks once a minute to see if either of the processes exited.
# This illustrates part of the heavy lifting you need to do if you want to run
# more than one service in a container. The container exits with an error
# if it detects that either of the processes has exited.
# Otherwise it loops forever, waking up every 60 seconds

while sleep 60; do
  XYZ=`echo 'exit' | telnet localhost 2181` 
  
  if [[ $XYZ == *"Escape"* ]]; then
    echo "found service."
    break;
  fi
done



# Start the first process
/usr/local/kafka/bin/kafka-server-start.sh config/server.properties & 

status=$?
if [ $status -ne 0 ]; then
  echo "Failed to start my_first_process: $status"
  exit $status
fi



# Naive check runs checks once a minute to see if either of the processes exited.
# This illustrates part of the heavy lifting you need to do if you want to run
# more than one service in a container. The container exits with an error
# if it detects that either of the processes has exited.
# Otherwise it loops forever, waking up every 60 seconds

while sleep 60; do
  XYZ=`echo 'exit' | telnet localhost 2181` 
  
  if [[ $XYZ == *"Escape"* ]]; then
    echo "found service."
    break;
  fi
done

/usr/local/kafka/bin/kafka-topics.sh \
	--create --topic samples.events --replication-factor 1 \
  --partitions 1  --zookeeper localhost:2181
  

while sleep 60; do
	echo "...keepalive";
done
