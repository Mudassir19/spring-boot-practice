FROM openjdk:11
ADD target/test-0.0.1-SNAPSHOT.jar test-0.0.1-SNAPSHOT.jar
EXPOSE 9090
ENTRYPOINT ["java","-jar","test-0.0.1-SNAPSHOT.jar"]