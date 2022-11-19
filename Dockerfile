FROM openjdk:11
ARG JAR_FILE=tuan_5_v1.0.2.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT [ "java", "-jar", "app.jar"]