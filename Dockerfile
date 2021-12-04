FROM openjdk:8-jdk-alpine
MAINTAINER Tenisheva Nataliia <ntgraal@gmail.com>

COPY ["target/SpringBootHelloWorld-0.0.1.jar", "SpringBootHelloWorld.jar"]
EXPOSE 8081
VOLUME /tmp
ENTRYPOINT ["java", "-jar", "SpringBootHelloWorld.jar"]

