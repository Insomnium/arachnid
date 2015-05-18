#!/bin/bash
export MAVEN_OPTS="-agentlib:jdwp=transport=dt_socket,address=8123,server=y,suspend=n"

cmd="mvn"

while getopts 'p' opt; do
    case "$opt" in
        p)
            cmd="$cmd clean package"
            ;;
    esac
done

cmd="$cmd"

echo "Running: $cmd"
$cmd

java -jar target/arachnid-0.0.1-SNAPSHOT.jar --spring.config.location=arachnid.properties

