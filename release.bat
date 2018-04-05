@echo off
echo Clean, Build and Release current project (-DskiptTests = True)
mvn clean -DskipTests -Darguments=-DskipTests release:prepare release:perform -B