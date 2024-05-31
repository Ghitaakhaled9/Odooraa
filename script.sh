#!/usr/bin/env sh
sh /app/mvnw install -DskipTests
java -jar /app/target/Odooraa-0.0.1-SNAPSHOT.jar
