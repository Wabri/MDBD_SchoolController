
FROM openjdk:8

ARG fatjar

COPY /target/SchoolController-0.0.1-SNAPSHOT-all.jar /app/app.jar

WORKDIR /app

CMD ["java", "-jar", "app.jar", "mongo-host"]
