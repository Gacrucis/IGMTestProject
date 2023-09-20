# Gacrucis' IGM Dockerized Maven Spring Boot REST API

This repository contains a Dockerized Maven Spring Boot application with two endpoints:

- `/api/generate/html` for generating asynchronously generated HTML content.
- `/api/call-rate-limited` for calling a rate-limited mock API.

## Prerequisites

    Docker
    Docker Compose

## How to run this

1. Clone this repo:

 ```bash
git clone https://github.com/Gacrucis/IGMTestProject.git
cd IGMTestProject
   ```
Build and run with docker compose

```bash
docker-compose up --build
```

This command will start the Spring Boot application with `mvn spring-boot:run`

Access the application in your web browser or using a tool like curl:
- http://127.0.0.1:8080/api/generate/html
- http://127.0.0.1:8080/api/call-rate-limited