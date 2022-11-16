FROM openjdk:8-jre-alpine
ADD target/achat-1.0-SNAPSHOT.jar achat-1.0.jar
EXPOSE 8089
CMD ["java", "-jar", "/achat-1.0.jar"]