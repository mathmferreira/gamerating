# Game Rating

![Java](https://img.shields.io/badge/java_17-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring_boot_3-%236DB33F.svg?style=for-the-badge&logo=springboot&logoColor=white)
![Docker](https://img.shields.io/badge/docker_compose-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![Postgres](https://img.shields.io/badge/postgresql-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white)

## About the project

This is an API for rating Games and give your opinion about them. The project was built with the purpose of showing my development skills, in order to add to my project portfolio.
Here you will see the concepts of SOLID, DRY and Clean Code applied, such as generics and polymorphism even in the unit and integration tests. Feel free to take a look at the code.

#### This API allows you to:

- Rating and comment games
- Manage games for rating
- View all the games that you rated
- Manage your user

## Built With

Built in Spring Boot 3 with Spring Security 6, this stateless API uses an encrypted JWT for authentication and JUnit 5 for unit testing purposes, 
such as RestAssuredMockMvc for integration tests.

### Frameworks

- Spring Boot 3
- Spring Security 6
- Gradle
- Docker Compose
- Flyway
- Lombok
- JWT (Nimbus Jose)
- JUnit 5
- RestAssured
- MockMvc
- OpenAPI 3 (Swagger)

## Getting Started

Before getting started, make sure that you followed the prerequisites instructions section.

**For Developers**: you can run this project on your IDE. 
Just make sure that Java 17, Gradle and Docker Desktop are properly configured.

### Prerequisites

You'll must have Java 17 installed and the JAVA_HOME path correctly configured in the environment variables of your machine.
You can download it [here](https://adoptium.net/temurin/releases/).

Check if everything is ok by opening your terminal and typing the command below:

```
java -version
```

It will return on the console something like this:

```
java version "17.0.8" 2023-07-18 LTS
Java(TM) SE Runtime Environment (build 17.0.8+9-LTS-211)
Java HotSpot(TM) 64-Bit Server VM (build 17.0.8+9-LTS-211, mixed mode, sharing)
```

Install the latest version of Gradle, for you be able to run the application. 
Follow the instructions in the official Gradle documentation [here](https://gradle.org/install/).

Check if everything is ok by opening your terminal and typing the command below:

```
gradle -v
```

It will return on the console the installed version, for example:

```
------------------------------------------------------------
Gradle 8.3
------------------------------------------------------------
```

The project uses **Docker Compose** for containerization of the database. You'll need to install docker compose in your local machine to run the database locally.
You can access the docker documentation for installation and configuration instructions [here](https://docs.docker.com/engine/install/).

After the installation, make sure that your Docker is running.

### Running the application

Once you have every prerequisite installed and configured properly, open your terminal and go to the project root folder and follow to the folder bellow: 

```
src > main > java > com > example > gamerating
```

You can run the application by executing the command below in the folder mentioned:

```
./gradlew bootRun
```

The application will be started, and the resources will be available at:

```
http:/localhost:8080
```

### API Docs

Once you have the application running, you can access the documentation of the API in the URL:

```
http://localhost:8080/swagger-ui/index.html
```
