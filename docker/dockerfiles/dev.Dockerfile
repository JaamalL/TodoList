FROM eclipse-temurin:21-jdk-jammy AS builder

WORKDIR /app
COPY . .

# ./mvnw used for consistency and portability across different environments
# -DskipTests disables tests
RUN ./mvnw clean package -DskipTests


FROM eclipse-temurin:21-jre-jammy

WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
