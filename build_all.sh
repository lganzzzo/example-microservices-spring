#!/bin/sh

build_service () {

    echo "building project $1"

    cd "$1/"
    ./mvnw clean package -DskipTests

    cd ../

}

build_service UserService
build_service BookService
build_service Facade
