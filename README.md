# CS-scholars-backend

Backend for CS564 project in UW-Madison

## quick start

make sure that you have configured [Java](https://www.java.com/en/), [Docker](https://docker.com) and [Maven](https://maven.apache.org/) environment.



1. run the project

```bash
docker compose up -d # start the database
mvn compile     # compile the project
mvn exec:java   # run the project
```

2. run the test

```bash
mvn test
```
