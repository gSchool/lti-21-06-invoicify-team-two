FROM openjdk:8
ADD target/invoicify-0.0.1-SNAPSHOT.jar invoicify-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar","invoicify-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080