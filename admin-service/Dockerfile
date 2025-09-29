# ---------- Build stage ----------
FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /app

COPY . .

# build all modules so common-lib is installed locally
RUN mvn -B clean install -DskipTests

# ---------- Runtime stage ----------
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=builder /app/admin-service/target/admin-service-1.0.0.jar app.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "app.jar"]
