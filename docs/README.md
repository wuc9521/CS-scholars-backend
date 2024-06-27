# README

```sql
/* tables in the database */

CREATE TABLE Person (
    pid INT PRIMARY KEY,
    name VARCHAR(255),
    major VARCHAR(255),
    hindex INT
);
CREATE TABLE Location (
    locid INT PRIMARY KEY,
    loc_name VARCHAR(255),
    city VARCHAR(255),
    state VARCHAR(255),
    country VARCHAR(255)
);
CREATE TABLE Publication (
    pubid INT PRIMARY KEY,
    pmid VARCHAR(255),
    doi VARCHAR(255)
);
CREATE TABLE Grants (
    grantid INT PRIMARY KEY,
    budget_start DATE
);
```
