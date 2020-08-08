@echo off
set localdir=%~dp0
call mvn clean package
pause

java -cp "cli/target/cli-1.0-SNAPSHOT-jar-with-dependencies.jar" io.toyyang.cli.Main