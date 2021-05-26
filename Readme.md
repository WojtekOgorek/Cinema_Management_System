# Cinema Management System

#### -> UNDER CONSTRUCTION <-> ALMOST FINISHED <-

Cinema Management System is a small REST-API that provides management system for cinema network. MySql database contains
data of cities, cinemas, cinema_rooms, movies, seances, seats, tickets, users, user favourites. It gives the user
possibility to buy, reserve ticket/s on picked seances in different cities/cinemas. It lets admin to manage CRUD system,
get statistics from db and create cinema finance policy. The Rest-Api is created based on DDD and TDD convention. The
domain module is fully encapsulated from rest of the application.

###Technologies used: Java, Maven, Jdbi, Spark, Spring.

## Installation

Use maven -> [link](https://maven.apache.org/download.cgi) <- to install client product shop.

```bash
#main folder
mvn clean install
#go to ui folder 
cd infrastructure
#go to target folder
cd target
#start app
java -jar --enable-preview infrastructure.jar
```

## Usage

Full api documentation published on Postman web:

https://web.postman.co/workspace/My-Workspace~ba933dc2-3236-4662-884a-26436cfa543e/documentation/13022165-0ff528ff-31ba-4dc3-9bad-c48aa3327b2b