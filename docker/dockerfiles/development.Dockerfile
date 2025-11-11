# Builder (builds the project with necessary dependencies)
# Uses JDK image suitable for running Maven
FROM eclipse-temurin:21-jdk-jammy AS builder

WORKDIR /app

# Caching strategy provides better project build time
# Main idea of caching strategy is to isolate stable dependencies
# Hence we isolate dependencies from pom.xml, because /src is very dynamic

# Copy necessary Maven files to install dependencies
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn

# ./mvnw used for consistency and portability across different environments
# -DskipTests disables tests
# Download all dependencies into the local Maven repository (.m2) inside the container
# The '--mount=type=cache,target=/root/.m2' persists dependency data between builds
RUN --mount=type=cache,target=/root/.m2 \
    ./mvnw dependency:go-offline -B

COPY src/ ./src

RUN --mount=type=cache,target=/root/.m2 \
    ./mvnw clean package -DskipTests


# Runner (minimal runtime image, runs application provided by the builder)
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

# --from=builder - an option, specifies the source for a COPY instruction
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
