FROM openjdk:21-jdk-alpine
VOLUME /tmp
add docker_local_https.jar docker_boot.jar
RUN sh -c 'touch /docker_boot.jar'
ENTRYPOINT ["java", "-jar", "/docker_boot.jar"]

EXPOSE 443