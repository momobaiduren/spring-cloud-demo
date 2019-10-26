dockFROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} spring-cloud-demo-provider-ba.jar
ENTRYPOINT ["java","-jar","/spring-cloud-demo-provider-ba.jar"]