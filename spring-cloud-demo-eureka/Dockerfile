FROM openjdk:8-jdk-alpine
VOLUME /tmp
#ARG JAR_FILE
ARG JAR_FILE
COPY ${JAR_FILE} spring-cloud-demo-eureka.jar
#COPY ${JAR_FILE} spring-cloud-demo-eureka.jar
ENTRYPOINT ["java","-jar","/spring-cloud-demo-eureka.jar"]