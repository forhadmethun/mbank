# About 
The project consists of implementing 2 services:
 - Account to keep track of current accounts and balances
 - Reporting to keep track of all transactions on the account for reporting purposes
 
 Both Account and reporting service contain integration tests and test coverage are
 80% +.
 
# Technologies Used
  - Java 11+
  - SpringBoot
  - JPA / Hibernate
  - MyBatis
  - Gradle
  - Postgres
  - RabbitMQ
  - JUnit
  
# Environment Setup 
The prerequisite to set-up the environment is [Java 11+](https://www.google.com/search?sxsrf=ALeKk00w29150FaDFXOwluYtW26vardUFg%3A1601836529895&ei=8RV6X6n9Ne_jkgWJjaTQBQ&q=jdk+14+setup&oq=jdk+14+setup&gs_lcp=CgZwc3ktYWIQAzICCAA6BAgAEEc6BwgAEBQQhwJQ7zlYykBgqEJoAHADeACAAZoBiAGYBZIBAzAuNZgBAKABAaoBB2d3cy13aXrIAQjAAQE&sclient=psy-ab&ved=0ahUKEwjpueiKypvsAhXvsaQKHYkGCVoQ4dUDCA0&uact=5),  [docker](https://docs.docker.com/get-docker/) and [docker-composer](https://docs.docker.com/compose/install/). Once all these things are installed then go to [/environment-setup](https://github.com/forhadmethun/mbank/tree/master/environment-setup) directory to setup PostgreSQL & RabbitMQ. 

# Running the Services
## To run account-service
 - open a terminal and navigate to `mbank-accountservice` directory
 - execute the following commands
```
./gradlew bootJar
nohup java -jar build/libs/ mbank-accountservice-0.0.1-SNAPSHOT.jar > /dev/null 2>&1 &
```
## To run report-service
 - open a terminal and navigate to `mbank-reportservice` directory

```
./gradlew bootJar
java -jar build/libs/mbank-reportservice-0.0.1-SNAPSHOT.jar
nohup java -jar build/libs/mbank-reportservice-0.0.1-SNAPSHOT.jar > /dev/null 2>&1 &
```
# Doc
 - [Click here to see the example of REST end points](https://documenter.getpostman.com/view/902298/TVRg6p2h#95a5f8e7-097e-49d2-be9c-38f37bb1f31f)
