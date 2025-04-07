# GrpcDemo Project

## Overview
This project consists of two microservices:
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

### 3. Run the Services
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

### 4. Test gRPC-Enabled REST Endpoints
Since the `TeamService` internally communicates with `PlayerService` via gRPC, testing the REST endpoint of `TeamService` will also validate the gRPC interaction.

#### Fetch Players of a Team
```sh
curl -X GET http://localhost:8081/teams/{teamId}
```

