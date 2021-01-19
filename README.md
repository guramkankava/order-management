# order-management
Demo project utilizing spring boot and mongodb

# Description

Project has 4 HTTP POST endpoints, each endpoint can recive order object.

  - mobile
  - utility
  - charity
  - bank
  
Each endpoint has set of rules validated by javax validator.

Objects are saved in mongo db.

Scheaduler runs once a 30 minuts, pulls orders from DB and conducts mock http to server simulating data synchronization.

Project also has WEB-mvc tests and junit tests.

## How to start the application

In order to start application mongo database is needed.

Project provides docker-compose.yml file, for convenience to start application without need to install mondo to local machine.

In order to start application without downloading the mongo database, navigate to project root and issue command

## docker-compose up;

In order to stop the application, navigate to project root and issue command ##docker-compose stop
