#!/bin/bash
set -e

mvn clean package
java -cp "cli/target/cli-1.0-SNAPSHOT-jar-with-dependencies.jar" io.toyyang.cli.Main