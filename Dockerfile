FROM openjdk:8-jdk-alpine
EXPOSE 8083
ADD target/TpAchat.war TpAchat.war
ENTRYPOINT ["java","-jar","/TpAchat.war"]