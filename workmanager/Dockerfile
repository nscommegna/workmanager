FROM openjdk:11
ADD target/workmanager-api-docker.war workmanager-api-docker.war
ENTRYPOINT ["java", "-jar","workmanager-api-docker.war"]
EXPOSE 8080