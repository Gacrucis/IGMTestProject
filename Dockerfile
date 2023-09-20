FROM maven:3.8.3-openjdk-17

WORKDIR /app

# For docker we only need source files and pom.xml
COPY pom.xml .
COPY src ./src

# Compile and package
RUN mvn clean package

# This app runs on port 8080
EXPOSE 8080

# Run spring boot
CMD ["mvn", "spring-boot:run"]