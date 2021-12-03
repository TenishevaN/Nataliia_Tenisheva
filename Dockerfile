FROM openjdk:8-jre-alpine
MAINTAINER Tenisheva Nataliia <ntgraal@gmail.com>

COPY ["target/jar-file-name.jar", "jar-file-name.jar"]
EXPOSE 8081

ENTRYPOINT ["java", "-jar", "jar-file-name.jar"]