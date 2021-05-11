FROM openjdk:11-jre-slim
MAINTAINER baeldung.com
COPY build/libs/java-service-1.0-SNAPSHOT.jar java-service-1.0-SNAPSHOT.jar
ENTRYPOINT java -jar -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8000 /java-service-1.0-SNAPSHOT.jar