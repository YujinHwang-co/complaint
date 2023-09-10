FROM openjdk:17-jdk-slim

MAINTAINER YUJIN <bineex2@naver.com>

ADD /build/libs/*.jar app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]