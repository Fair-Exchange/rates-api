FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/rates-api.jar
COPY ${JAR_FILE} app.jar
EXPOSE 80
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]