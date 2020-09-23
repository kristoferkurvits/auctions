#### Stage 1: Build the application
FROM openjdk:11 as build


WORKDIR /app


COPY mvnw .
COPY .mvn .mvn


COPY pom.xml .


RUN ./mvnw dependency:go-offline -B


COPY src src


RUN ./mvnw package -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

#### Stage 2: A minimal docker image with command to run the app 
FROM openjdk:11

ARG DEPENDENCY=/app/target/dependency


COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

ENTRYPOINT ["java","-cp","app:app/lib/*","com.Kurvits.bacchusback.BacchusBackApplication"]
