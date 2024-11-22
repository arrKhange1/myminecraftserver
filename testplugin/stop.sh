#!/bin/bash

# kill all processes launched from java except Main(intellij) and Jps itself
PROCESSES_TO_KILL=$(jps | grep -vE "Jps|Main" | awk -F' ' '{print $1}')
for pid in $PROCESSES_TO_KILL; do kill -9 $pid; done