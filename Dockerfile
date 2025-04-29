#base model
from eclipse-temurin:23.0.2_7-jdk-alpine
#containers root's directory
workdir /app
#importing src content
copy ./src /app/src
copy ./pom.xml /app
#mvn requireds
copy ./.mvn /app/.mvn
copy ./mvnw /app
#dependencies
run ./mvnw dependency:go-offline
#build
run ./mvnw clean install
#run
entrypoint ["java", "-jar", "/app/target/hex-selling-points-0.0.1-SNAPSHOT.jar"]