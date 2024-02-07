# CRUD Demo

![Java Version](https://img.shields.io/badge/Java-17-informational?style=flat&logo=java)
![Spring Boot Version](https://img.shields.io/badge/Spring%20Boot-3.2.1-brightgreen?style=flat&logo=spring)
![MySQL Connector](https://img.shields.io/badge/MySQL%20Connector-runtime-yellow?style=flat&logo=mysql)

A simple CRUD (Create, Read, Update, Delete) API project for managing employee records.

## Description

This project provides a RESTful API for basic CRUD operations on an employee database. It leverages the following technologies:

- [Spring Boot](https://spring.io/projects/spring-boot) for building robust and scalable applications.
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa) for easy data access with Hibernate.
- [MySQL](https://www.mysql.com/) for data storage and retrieval.

## How to Run

1. Ensure you have Java 17 and Maven installed.
2. Clone this repository.
3. Configure your MySQL database details in the application.properties file.
    - By default, the database is set to localhost:3036 with root as both the username and password. The database should be created using the sql script inside the "sql-scripts" folder.
4. Go to the repository's location
5. Run the application using Maven:
   ```bash
   mvn spring-boot:run
6. Go to localhost:8080/employees by default, if you haven't changed the app's port. It requires external software as Postman to post, put or delete. Thus, the version in crudMVCDemo is much better, as it implements a UI to add, delete or update employees.