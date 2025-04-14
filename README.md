# Spring-Grpc-Demo Project

## Overview
This is a Spring Boot project demonstrating interservice communication between microservices using gRPC and REST APIs. It consists of two microservices:
1. **Player Service**: Manages players and provides gRPC streaming to fetch players by team.
2. **Team Service**: Retrieves team players by communicating with Player Service via gRPC.

The project is built using **Spring Boot**, **Spring Data JPA**, and **gRPC**.

## Features
- **Player Management**: Create and retrieve players.
- **Team Management**: Fetch players of a specific team.
- **gRPC Server Streaming**: Efficiently fetches multiple players in a streaming manner.
- **REST API (For Player nd Team Management)**: CRUD operations via HTTP.

## Technologies Used
- **Spring Boot**
- **Spring Data JPA**
- **gRPC**
- **MySQL** (or any relational database)
- **Maven**

---

## Project Structure
```
├── playerService
│   ├── pom.xml
│   └── src
│       └── main
│           ├── java
│           │   └── com
│           │       └── nashtech
│           │           └── playerService
│           │               ├── controller
│           │               │   └── PlayerController.java
│           │               ├── entity
│           │               │   └── Player.java
│           │               ├── PlayerServiceApplication.java
│           │               ├── repository
│           │               │   └── PlayerRepository.java
│           │               └── service
│           │                   ├── implementation
│           │                   │   ├── PlayerDatabaseServiceImpl.java
│           │                   │   └── PlayerGrpcServiceImpl.java
│           │                   └── PlayerDatabaseService.java
│           ├── proto
│           │   └── player_with_team.proto
│           └── resources
│               └── application.properties
├── README.md
└── teamService
    ├── pom.xml
    ├── src
    │   └── main
    │       ├── java
    │       │   └── com
    │       │       └── nashtech
    │       │           └── teamService
    │       │               ├── controller
    │       │               │   └── TeamController.java
    │       │               ├── entities
    │       │               │   ├── Player.java
    │       │               │   └── Team.java
    │       │               ├── repository
    │       │               │   └── TeamRepository.java
    │       │               ├── service
    │       │               │   ├── implementation
    │       │               │   │   └── TeamServiceImpl.java
    │       │               │   └── TeamService.java
    │       │               └── TeamServiceApplication.java
    │       ├── proto
    │       │   └── player_with_team.proto
    │       └── resources
    │           └── application.properties
```

---

## Player Service
### REST API Endpoints
| Method | Endpoint | Description |
|--------|---------|-------------|
| POST   | `/players` | Create a new player |
| GET    | `/players/{playerId}` | Get a specific player by ID |
| GET    | `/players` | Get all players |


### gRPC Endpoint
- **Service:** `playerService`
- **Method:** `getPlayersOfTeam(request) → stream PlayerResponse`

---

## Team Service
### REST API Endpoints
| Method | Endpoint | Description |
|--------|---------|-------------|
| POST   | `/teams` | Create a new team |
| GET    | `/teams/{teamId}` | Get a specific team by ID |
| GET    | `/teams` | Get all teams |

### gRPC Client Method
- Calls `PlayerService` to get players for a specific team using **server streaming**.
- Uses `PlayerServiceGrpc.PlayerServiceBlockingStub` to fetch players.

---

## Setup Instructions

### 1. Clone the Repository
```sh
git clone https://github.com/ibrahimnadra/GrpcDemo
cd GrpcDemo
```

### 2. Build the Services
```sh
cd playerService
mvn clean install
cd ../teamService
mvn clean install
```

### 3. Mark Directory as Source Root
1. Right-click on the directory: playerService/target/generated-sources.
2. Choose "Mark Directory as".
3. Select "Sources Root"
4. Do the same for teamService

### 4. Run the Services
#### Start the Player Service
```sh
cd playerService
mvn spring-boot:run
```
#### Start the Team Service
```sh
cd teamService
mvn spring-boot:run
```

### 5. Test gRPC-Enabled REST Endpoints
1. Create a team and its players using the TeamService and PlayerService.
You can use tools like Postman or run cURL commands to make the necessary API calls.
2. Once the team and players have been created, invoke the TeamService endpoint to retrieve the team details.
This endpoint will also fetch and include the associated players, demonstrating the gRPC-enabled communication between services.

#### Fetch Players of a Team
```sh
curl -X GET http://localhost:8081/teams/{teamId}
```

