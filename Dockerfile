FROM openjdk:17-alpine3.14
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]