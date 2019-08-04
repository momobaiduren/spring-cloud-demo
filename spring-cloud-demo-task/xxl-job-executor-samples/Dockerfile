FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} xxl-job-executor-samples.jar
ENTRYPOINT ["java","-jar","/xxl-job-executor-samples.jar"]