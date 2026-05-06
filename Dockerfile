FROM eclipse-temurin:17

WORKDIR /app

COPY . .

RUN chmod +x mvnw
RUN ./mvnw clean install -DskipTests

EXPOSE 10000

CMD ["java","-jar","target/devpulse_project_harshini-0.0.1-SNAPSHOT.jar"]