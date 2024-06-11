FROM amazoncorretto:17-alpine-jdk

COPY target/icl-1.3.war app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]