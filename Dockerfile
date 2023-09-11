FROM openjdk:17-jdk-slim
MAINTAINER YUJIN <bineex2@naver.com>
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8081
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]