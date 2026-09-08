@echo off
chcp 65001 >nul
call mvn install -DskipTests -q
cd examples\Demo
call mvn compile exec:java -q
cd ..\..
pause
