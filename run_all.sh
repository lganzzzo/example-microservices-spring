#!/bin/sh

build_service () {

    echo "running project $1"

    java -jar -Xmx512m "$1/target/$1-0.0.1-SNAPSHOT.jar" &

}

build_service UserService
build_service BookService
build_service Facade
