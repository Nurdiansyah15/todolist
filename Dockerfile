FROM openjdk:17-alpine

ADD target/to-do-list-app-0.0.1-SNAPSHOT.jar to-do-list-app.jar

CMD ["java", "-jar", "to-do-list-app.jar"]