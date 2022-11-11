FROM openjdk:8-jdk-alpine
EXPOSE 8083
RUN ls
ADD target/achat-1.0-SNAPSHOT.jar achat-1.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/achat-1.0-SNAPSHOT.jar"]