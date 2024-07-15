# README

## Data

Our data is accessed [here](https://zenodo.org/records/10717537), and some explanations of attributes can be accessed [here](https://academictree.org/export.php)

Our idea comes from the famous open source project [Academic Tree](https://academictree.org/)

## Database

```sql
/* tables in the database */

CREATE TABLE person (
    pid INT PRIMARY KEY,
    name VARCHAR(255),
    major VARCHAR(255),
    hindex INT
);
CREATE TABLE location (
    locid INT PRIMARY KEY,
    loc_name VARCHAR(255),
    city VARCHAR(255),
    state VARCHAR(255),
    country VARCHAR(255)
);
CREATE TABLE publication (
    pubid INT PRIMARY KEY,
    pmid VARCHAR(255),
    doi VARCHAR(255)
);
CREATE TABLE grants (
    grantid INT PRIMARY KEY,
    budget_start DATE
);

CREATE TABLE "in" (
    pid INT,
    locid INT,
    PRIMARY KEY (pid, locid)
);
CREATE TABLE publish (
    pid INT,
    pubid INT,
    PRIMARY KEY (pid, pubid)
);
CREATE TABLE has (
    pid INT,
    grantid INT,
    PRIMARY KEY (pid, grantid)
);
```

## APIs

### Person

```shell
/api/scholar            # get all scholars
/api/scholar/<int:pid>  # get a scholar by pid
```
