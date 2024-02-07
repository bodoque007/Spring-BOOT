# Very Basic CRUD Web Application

This is a very basic CRUD (Create, Read, Update, Delete) web application built using the following technologies:

- [Spring Boot](https://spring.io/projects/spring-boot) 
 ![Spring Boot Logo](https://www.vectorlogo.zone/logos/springio/springio-icon.svg)
- [MySQL](https://www.mysql.com/) 
![MySQL Logo](https://www.vectorlogo.zone/logos/mysql/mysql-icon.svg)
- [JPA (Java Persistence API)](https://www.oracle.com/java/technologies/persistence-jsp.html)
 ![JPA Logo](https://www.vectorlogo.zone/logos/java/java-icon.svg)
- [Thymeleaf](https://www.thymeleaf.org/) 
![Thymeleaf Logo](https://seeklogo.com/images/T/thymeleaf-logo-7EA70A2DC4-seeklogo.com.png)

## Description

This CRUD web application allows you to perform basic operations on data entities (employees) using a user-friendly interface.

## How to Run

1. Ensure you have Java version 17 (or higher) installed.
2. Clone this repository.
3. Go to the repository's location
4. Configure your MySQL database details in the application (By default, "root" user with password "root" with database on localhost:3306) and execute the script on "sql-scripts" folder to set up the example database.
5. Run the application using Maven:
   ```bash
   mvn spring-boot:run
6. By default, the main site is on http://localhost:8080/employees/list URL, in which you can add, delete and update the entities (employees).
