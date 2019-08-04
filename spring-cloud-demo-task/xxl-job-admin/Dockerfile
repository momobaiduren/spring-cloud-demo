FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} xxl-job-admin.jar
ENTRYPOINT ["java","-jar","/xxl-job-admin.jar"]