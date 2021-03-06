# Pokemon GraphQL API (Kotlin, Gradle, Ktor, GraphQL, Kotest, Docker, GraphQL Kotlin)
This project provides a GraphQL API where Pokemon data can be queried, and will be used as the 
backend API to a react native application of mine (https://github.com/AA25/pokedexGQL)

![](https://j.gifs.com/P7RJjn.gif)

The application in currently running as docker container in AWS via Amazon ECS.

<img src="https://i.imgur.com/0uUI2Pl.png">

## Usage
Run project using either docker or gradle (see commands below) and
Visit `localhost:8088/playground` to query the graphQL API and view endpoint documentation.
```
query{
  pokemon(params: {ids: [1,4,7,25]}){
    name,
    type
  }
}

query{
  pokemons{
    name
    games {
     name
    }
  }
}
```
Another option is to curl a POST request to `localhost:8088/graphql` with the following body
```
curl -X POST 'http://0.0.0.0:8088/graphql' \
-H 'Content-Type: application/json' \
--data-raw '{"query":"query {\n pokemon(params: {ids: [1,4,7,25]}){\n name,\n type \n } \n } \n","variables":{}}'
```

## Using Docker Compose
```
# Build the image
docker-compose build

# Start the basic application in Docker
docker-compose up
```

## Using Gradle (Requires Java 1.8)
```
# To run tests for a project, Gradle will not run tests for area's that have not had any changes.
./gradlew test

# To build an application bundle, after compiling and running test.
./gradlew build

# Remove previous build cache files, useful to force recompilation and test.
./gradlew clean

# Run the basic application
./gradlew run

# Detects changes made to the class path being watched (in the application.conf file) and rebuilds the project
# Use while project is already running
./gradlew -t installDist
```

## Resources
https://ktor.io/quickstart/guides/api.html

https://expediagroup.github.io/graphql-kotlin/docs/server/graphql-server

https://kotest.io/docs/quickstart
